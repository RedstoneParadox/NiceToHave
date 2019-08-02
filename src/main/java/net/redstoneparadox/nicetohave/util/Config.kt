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
        configObject.putDefault("items", JsonObject(), null)
        configObject.putDefault("blocks", JsonObject(), null)
        configObject.putDefault("misc", JsonObject(), null)

        val itemsCategory = configObject.get(JsonObject::class.java, "items")
        val blocksCategory = configObject.get(JsonObject::class.java, "blocks")
        val miscCategory = configObject.get(JsonObject::class.java, "misc")

        //Items
        itemsCategory!!.putDefault("chain_link", JsonPrimitive(true), "Set to false to disable chain-link crafting.")
        itemsCategory.putDefault("dynamite", JsonPrimitive(true), "Set to false to disable dynamite.")
        itemsCategory.putDefault("wrench", JsonPrimitive(true), "Set to false to disable the wrench.")

        //Blocks
        blocksCategory!!.putDefault("gold_button", JsonPrimitive(true), "Set to false to disable gold buttons.")
        blocksCategory.putDefault("variable_redstone_emitter", JsonPrimitive(true), "Set to false to disable the Variable Redstone Emitter.")

        //Misc
        miscCategory!!.putDefault("dispenser_crop_planting", JsonPrimitive(true), "Set to false to disable dispensers planting crops.")
        miscCategory.putDefault("dispenser_ladder_placement", JsonPrimitive(true), "Set to false to disable dispensers breaking and placing ladders.")
    }

    fun save() {
        val configString = configObject.toJson(true, true)
        File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson").bufferedWriter().use { it.write(configString) }
    }
}