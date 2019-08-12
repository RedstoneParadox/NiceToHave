package redstoneparadox.nicetohave.util

import blue.endless.jankson.Jankson
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import blue.endless.jankson.impl.SyntaxError
import net.fabricmc.loader.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File
import java.io.IOException

object Config {

    private var configObject : JsonObject = JsonObject()
    private var hadError : Boolean = false

    //Types
    val boolType = Boolean::class.javaObjectType
    val doubleType = Double::class.javaObjectType

    init {
        load()
        setValues()
        save()
    }

    private fun load() {
        try {
            configObject = Jankson
                    .builder()
                    .build()
                    .load(File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson"))
        } catch (e : IOException) {
            NiceToHave.out("Couldn't find config file; all config values will be set to default and a new file will be created.")
        } catch (e : SyntaxError) {
            NiceToHave.error("Couldn't read the config file due to an hjson syntax error. Please fix the file or delete it to generate a new one. (Default config values will be used in the meantime).")
            e.message?.let { NiceToHave.error(it) }
            NiceToHave.error(e.lineMessage)
            hadError = true
        }
    }

    private fun setValues() {
        //Main
        configObject.putDefault("config_version", JsonPrimitive(1), "Stores the version of the config; used if the config format changes so that old values can be transferred to the new format. DO NOT EDIT.")

        val itemsCategory = addCategory("items", "Enable/Disable items.")
        val blocksCategory = addCategory("blocks", "Enable/Disable blocks.")
        val potionsCategory = addCategory("potions", "Enable/disable potions.")
        val worldCategory = addCategory("world", "Enable, disable, and configure various world gen features.")
        val miscCategory = addCategory("misc", "Miscellaneous settings.")

        //Items
        itemsCategory!!.putDefault("chain_link", JsonPrimitive(true), "Set to false to disable chain-links.")
        itemsCategory.putDefault("dynamite", JsonPrimitive(true), "Set to false to disable dynamite.")
        itemsCategory.putDefault("wrench", JsonPrimitive(true), "Set to false to disable the wrench.")
        itemsCategory.putDefault("fertilizer", JsonPrimitive(true), "Set to false to disable fertilizer.")

        //Blocks
        blocksCategory!!.putDefault("gold_button", JsonPrimitive(true), "Set to false to disable gold buttons.")
        blocksCategory.putDefault("analog_redstone_emitter", JsonPrimitive(true), "Set to false to disable the Analog Redstone Emitter.")
        //blocksCategory.putDefault("chain_link_fence", JsonPrimitive(true), "Set to false to disable chain-link fences.")
        blocksCategory.putDefault("trimmed_vine", JsonPrimitive(true), "Set to false to disable trimmed-vine blocks.")
        blocksCategory.putDefault("poles", JsonPrimitive(true), "Set to false to disable pole blocks.")

        //Potions
        potionsCategory!!.putDefault("insight", JsonPrimitive(true), "Set to false to disable the Potion of Insight.")
        //potionsCategory.putDefault("nectar", JsonPrimitive(true), "Set to false to disable Nectar.")

        //World
        worldCategory!!.putDefault("gold_in_rivers", JsonPrimitive(true), "Set to false to disable gold deposits in rivers.")
        worldCategory.putDefault("river_gold_percent", JsonPrimitive(10.0f), "Sets the spawn rate of river gold ore in a single river gold deposit. Does not set the spawn rate of the deposits themselves. [Range 0 to 100]")
        worldCategory.putDefault("disable_ponds", JsonPrimitive(true), "Disables small water and lava ponds. Set to false to re-enabled them.")

        //Misc
        miscCategory!!.putDefault("dispenser_crop_planting", JsonPrimitive(true), "Set to false to disable dispensers planting crops.")
        miscCategory.putDefault("dispenser_ladder_placement", JsonPrimitive(true), "Set to false to disable dispensers breaking and placing ladders and scaffolding.")
        miscCategory.putDefault("peaceful_bamboo_jungle", JsonPrimitive(true), "Disables hostile mob spawning in Bamboo Jungle and Bamboo Jungle hills biomes (Just like Mushroom Islands). Set to false to re-enable.")
    }

    private fun save() {
        if (!hadError) {
            val configString = configObject.toJson(true, true)
            File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson").bufferedWriter().use { it.write(configString) }
        }
    }

    private fun addCategory(name : String, comment : String): JsonObject {
        configObject.putDefault(name, JsonObject(), comment)
        return configObject.get(JsonObject::class.java, name)!!
    }

    fun <T> getItemOption(key : String, clazz : Class<T>, default : T): T {
        return getOption(key, clazz, default, "items")
    }

    fun <T> getBlockOption(key : String, clazz : Class<T>, default: T): T {
        return getOption(key, clazz, default, "blocks")
    }

    fun <T> getPotionOption(key : String, clazz : Class<T>, default: T): T {
        return getOption(key, clazz, default, "potions")
    }

    fun <T> getWorldOption(key: String, clazz : Class<T>, default : T): T {
        return getOption(key, clazz, default, "world")
    }

    fun <T> getMiscOption(key : String, clazz : Class<T>, default: T): T {
        return getOption(key, clazz, default, "misc")
    }

    private fun <T> getOption(key : String, clazz : Class<T>, default : T, category : String = ""): T {
        val categoryObject = if (category.isBlank()) configObject else configObject.get(JsonObject::class.java, category)
        val trueOptionName = if (category.isBlank()) key else "$category.$key"


        if (categoryObject != null) {
            val option : JsonPrimitive? = (categoryObject[key] as JsonPrimitive)

            if (option != null) {
                val value = option.value
                if (clazz.isInstance(value)) {
                    return clazz.cast(value)
                }
                else {
                    NiceToHave.error("Found value of `$value` for config option `$trueOptionName` but expected value of type `${classToType(trueOptionName, clazz)}`. ${defaultMsg(trueOptionName, default)}")
                }
            }
            else {
                NiceToHave.error("Could not find config option `$trueOptionName`. ${defaultMsg(trueOptionName, default)}")
            }
        }
        else {
            NiceToHave.error("Could not find config category `$key`. ${defaultMsg(trueOptionName, default)}")
        }

        return default
    }

    private fun <T> defaultMsg(option : String, default : T): String {
        return "Option '$option' will use default value of `$default`."
    }

    private fun classToType(option : String, clazz : Class<*>): String {
        when (clazz) {
            boolType -> return "boolean"
            doubleType -> return "double"
            else -> {
                NiceToHave.error("Unsupported type `$clazz` found as value of config option `$option`!")
                return clazz.toString()
            }
        }
    }
}