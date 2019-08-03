package net.redstoneparadox.nicetohave.util

import blue.endless.jankson.Jankson
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import blue.endless.jankson.impl.SyntaxError
import net.fabricmc.loader.FabricLoader
import net.redstoneparadox.nicetohave.NiceToHave
import java.io.File
import java.io.IOException

object Config {

    lateinit var configObject : JsonObject

    //Types
    val boolType = Boolean::class.javaObjectType

    fun load() {
        try {
            configObject = Jankson
                    .builder()
                    .build()
                    .load(File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson"))
        } catch (e : IOException) {
            NiceToHave.warn("Couldn't find config file; all config values will be set to default and a new file will be created.")
            configObject = JsonObject()
        } catch (e : SyntaxError) {
            NiceToHave.error("There was an error when reading the config file.")
            e.message?.let { NiceToHave.error(it) }
            NiceToHave.error(e.lineMessage)
        }
    }

    fun setValues() {
        //Main
        configObject.putDefault("config_version", JsonPrimitive(1), "Stores the version of the config; used if the config format changes so that old values can be transferred to the new format. DO NOT EDIT.")

        configObject.putDefault("items", JsonObject(), null)
        configObject.putDefault("blocks", JsonObject(), null)
        configObject.putDefault("world", JsonObject(), null)
        configObject.putDefault("misc", JsonObject(), null)

        val itemsCategory = configObject.get(JsonObject::class.java, "items")
        val blocksCategory = configObject.get(JsonObject::class.java, "blocks")
        val worldCategory = configObject.get(JsonObject::class.java, "world")
        val miscCategory = configObject.get(JsonObject::class.java, "misc")

        //Items
        itemsCategory!!.putDefault("chain_link", JsonPrimitive(true), "Set to false to disable chain-link crafting.")
        itemsCategory.putDefault("dynamite", JsonPrimitive(true), "Set to false to disable dynamite.")
        itemsCategory.putDefault("wrench", JsonPrimitive(true), "Set to false to disable the wrench.")

        //Blocks
        blocksCategory!!.putDefault("gold_button", JsonPrimitive(true), "Set to false to disable gold buttons.")
        blocksCategory.putDefault("variable_redstone_emitter", JsonPrimitive(true), "Set to false to disable the Variable Redstone Emitter.")

        //World
        worldCategory!!.putDefault("gold_in_rivers", JsonPrimitive(false), "Set to false to disable gold deposits in rivers.")

        //Misc
        miscCategory!!.putDefault("dispenser_crop_planting", JsonPrimitive(true), "Set to false to disable dispensers planting crops.")
        miscCategory.putDefault("dispenser_ladder_placement", JsonPrimitive(true), "Set to false to disable dispensers breaking and placing ladders.")
    }

    fun save() {
        val configString = configObject.toJson(true, true)
        File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson").bufferedWriter().use { it.write(configString) }
    }

    fun <T> getItemOption(key : String, type : Class<T>, default : T): T {
        return getOption(key, type, default, "items")
    }

    fun <T> getBlockOption(key : String, type : Class<T>, default: T): T {
        return getOption(key, type, default, "blocks")
    }

    fun <T> getWorldOption(key: String, type : Class<T>, default : T): T {
        return getOption(key, type, default, "world")
    }

    fun <T> getMiscOption(key : String, type : Class<T>, default: T): T {
        return getOption(key, type, default, "misc")
    }

    private fun <T> getOption(key : String, type : Class<T>, default : T, category : String = "main"): T {
        val categoryObject = if (category == "main") configObject else configObject.get(JsonObject::class.java, category)
        val option = (categoryObject!![key] as JsonPrimitive)

        val value = option.value
        if (type.isInstance(value)) {
            return type.cast(value)
        }
        else {
            NiceToHave.error("Error when reading config option '$key', Expected $type, found ${value.javaClass}. Option will use default value of $default.")
        }

        return default
    }
}