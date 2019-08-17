package redstoneparadox.nicetohave.util

import blue.endless.jankson.Jankson
import blue.endless.jankson.JsonElement
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import blue.endless.jankson.impl.SyntaxError
import net.fabricmc.loader.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File
import java.io.IOException

object OldConfig {

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
        configObject = Builder(configObject)
                //Items
                .addCategory("items", "Enable/Disable items.")
                .addTrueBool("chain_link", "Set to false to disable chain-links.")
                .addTrueBool("dynamite", "Set to false to disable dynamite.")
                .addTrueBool("wrench", "Set to false to disable the wrench.")
                .addTrueBool("fertilizer", "Set to false to disable fertilizer.")
                .addTrueBool("sweet_berry_juice", "Set to false to disable sweet berry juice")
                .addTrueBool("apple_juice", "Set to false to disable apple juice")
                //Blocks
                .addCategory("blocks", "Enable/Disable blocks.")
                .addTrueBool("gold_button", "Set to false to disable gold buttons.")
                .addTrueBool("analog_redstone_emitter", "Set to false to disable analog redstone emitters.")
                //.addTrueBool("chain_link_fence", "Set to false to disable chain-link fences.")
                .addTrueBool("trimmed_vine", "Set to false to disable trimmed-vines.")
                .addTrueBool("poles", "Set to false to disable pole blocks.")
                //Potions
                .addCategory("potions", "Enable/Disable potions.")
                .addTrueBool("insight", "Set to false to disable the Potion of Insight.")
                //.addTrueBool("nectar", "Set to false to disable Nectar.")
                //World
                .addCategory("world", "Various world features.")
                .addTrueBool("gold_in_rivers", "Set to false to disable gold deposits in rivers.")
                .addFloatRange("river_gold_percent", "Sets the spawn rate of river gold ore in a single river gold deposit. Does not set the spawn rate of the deposits themselves.", 10.0f, 0.0f, 100.0f)
                .addTrueBool("disable_ponds", "Set to false to re-enable water and lava ponds.")
                //Misc
                .addCategory("misc", "Miscellaneous settings.")
                .addTrueBool("dispenser_crop_planting", "Set to false to disable dispensers planting crops.")
                .addTrueBool("dispenser_ladder_placement", "Set to false to disable dispensers breaking and placing ladders and scaffolding.")
                .addTrueBool("peaceful_bamboo_jungle", "Disables hostile mob spawning in Bamboo Jungle and Bamboo Jungle hills biomes (Just like Mushroom Islands). Set to false to re-enable.")
                .addTrueBool("vehicle_pickup", "Allows you to pickup boats and minecarts by shift-clicking.")

                .build()
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
            val option : JsonElement? = categoryObject[key]

            if (option is JsonPrimitive) {
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

    private class Builder(val baseObject: JsonObject) {

        var currentCategory : JsonObject = baseObject

        fun addCategory(key : String, comment: String): Builder {
            baseObject.putDefault(key, JsonObject(), comment)
            currentCategory = baseObject.get(JsonObject::class.java, key)!!
            return this
        }

        fun addTrueBool(key : String, comment: String): Builder {
            currentCategory.putDefault(key, JsonPrimitive.TRUE, "${comment} [Values: true/false]")
            return this
        }

        fun addFalseBool(key : String, comment: String): Builder {
            currentCategory.putDefault(key, JsonPrimitive.FALSE, "${comment} [Values: true/false]")
            return this
        }

        fun addFloat(key: String, comment: String, value : Float): Builder {
            currentCategory.putDefault(key, JsonPrimitive(value), "${comment} [Values: any number]")
            return this
        }

        fun addFloatRange(key: String, comment: String, value: Float, min : Float, max : Float): Builder {
            currentCategory.putDefault(key, JsonPrimitive(value), "${comment} [Values: $min to $max]")
            return this
        }

        fun build(): JsonObject {
            baseObject.putDefault("config_version", JsonPrimitive(1), "Stores the version of the config; used if the config format changes so that old values can be transferred to the new format. DO NOT EDIT.")
            return baseObject
        }
    }
}