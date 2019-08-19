package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.Jankson
import blue.endless.jankson.JsonElement
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import blue.endless.jankson.impl.SyntaxError
import io.github.cottonmc.libcd.condition.ConditionalData
import net.fabricmc.loader.FabricLoader
import net.minecraft.util.Identifier
import redstoneparadox.nicetohave.NiceToHave
import java.io.File
import java.io.IOException

object Config {

    private val mainCategory : ConfigCategory

    private var hadError = false

    //Types
    val boolType = Boolean::class.javaObjectType
    val doubleType = Double::class.javaObjectType
    val intType = Int::class.javaObjectType

    init {
        val hjsonFile = File(FabricLoader.INSTANCE.configDirectory, "nicetohave.hjson")
        val json5File = File(FabricLoader.INSTANCE.configDirectory, "nicetohave.json5")

        var configObject = JsonObject()

        try {
            configObject = Jankson
                    .builder()
                    .build()
                    .load(if (json5File.exists()) json5File else hjsonFile)
        } catch (e : IOException) {
            NiceToHave.out("Couldn't find config file; all config values will be set to default and a new file will be created.")
        } catch (e : SyntaxError) {
            NiceToHave.error("Couldn't read the config file due to an hjson syntax error. Please fix the file or delete it to generate a new one. (Default config values will be used in the meantime).")
            e.message?.let { NiceToHave.error(it) }
            NiceToHave.error(e.lineMessage)
            hadError = true
        }

        if (hjsonFile.exists()) {
            NiceToHave.out("Nice to Have now uses JSON5 as the config format for better editor support. Please delete the hjson file as all files will now be written to the json5 file.")
        }

        mainCategory = Builder(configObject)
                .addMetaInt("config_version", "Config format version.", 1)
                .newCategory("items", "Enable/Disable items.")
                .addBool("chain_link", "Set to false to disable chain-links.")
                .addBool("dynamite", "Set to false to disable dynamite.")
                .addBool("wrench", "Set to false to disable the wrench.")
                .addBool("fertilizer", "Set to false to disable fertilizer.")
                .addBool("sweet_berry_juice", "Set to false to disable sweet berry juice.")
                .addBool("apple_juice", "Set to false to disable apple juice.")
                .endCategory()
                .newCategory("blocks", "Enable/Disable blocks.")
                .addBool("gold_button", "Set to false to disable gold buttons.")
                .addBool("analog_redstone_emitter", "Set to false to disable analog redstone emitters.")
                //.addBool("chain_link_fence", "Set to false to disable chain-link fences.")
                .addBool("trimmed_vine", "Set to false to disable trimmed-vines.")
                .addBool("poles", "Set to false to disable pole blocks.")
                .endCategory()
                .newCategory("recipes", "Adding recipes and tweaking them.")
                .addBool("increased_rail_output", "Activator, detector, and powered rail recipes give 16 rails instead of 6.")
                .addBool("uncraft_netherwart_block", "Recipe for crafting Netherwart Blocks back into 9 Netherwart.")
                .addBool("melon_to_slices", "Allows watermelons to be crafted into 9 watermelon slices.")
                .endCategory()
                .newCategory("potions", "Enable/Disable potions.")
                .addBool("insight", "Set to false to disable the Potion of Insight.")
                //.addBool("nectar", "Set to false to disable Nectar.")
                .endCategory()
                .newCategory("world", "Various world features.")
                .addBool("gold_in_rivers", "Set to false to disable gold deposits in rivers.")
                .addRange("river_gold_percent", "Sets the spawn rate of river gold ore in a single river gold deposit. Does not set the spawn rate of the deposits themselves.", 10.0, 0.0, 100.0)
                .addBool("disable_ponds", "Set to false to re-enable water and lava ponds.")
                .endCategory()
                .newCategory("misc", "Miscellaneous settings.")
                .addBool("dispenser_crop_planting", "Set to false to disable dispensers planting crops.")
                .addBool("dispenser_ladder_placement", "Set to false to disable dispensers breaking and placing ladders and scaffolding.")
                .addBool("peaceful_bamboo_jungle", "Disables hostile mob spawning in Bamboo Jungle and Bamboo Jungle hills biomes (Just like Mushroom Islands). Set to false to re-enable.")
                .addBool("vehicle_pickup", "Allows you to pickup boats and minecarts by shift-clicking.")
                .endCategory()
                .build()

        save()

        ConditionalData.registerCondition(Identifier("nicetohave", "config_true")) {
            if (it is String) {
                return@registerCondition getBool(it)
            }
            else if (it is List<*>) {
                for (element in (it as List<JsonElement>)) {
                    if (element !is JsonPrimitive) return@registerCondition false
                    val key : String = element.value as? String ?: return@registerCondition false
                    if (!getBool(key)) return@registerCondition false
                }
                return@registerCondition true
            }
            return@registerCondition false
        }
    }

    fun getBool(key : String, default : Boolean = true): Boolean {
        return getOption(key, default, boolType)
    }

    fun getDouble(key: String, default : Double): Double {
        return getOption(key, default, doubleType)
    }

    fun getRange(key: String, default : Double): Double {
        return getOption(key, default, doubleType)
    }

    fun getMetaInt(key: String, default : Int): Int {
        return getOption(key, default, intType)
    }

    private fun <T : Any> getOption(key: String, default : T, optionType : Class<T>): T {
        val keySequence = key.splitToSequence(".")
        return mainCategory.getOption(keySequence, default, key, optionType)
    }

    private fun save() {
        if (!hadError) {
            val configString = mainCategory.serialize(JsonObject()).toJson(true, true)
            File(FabricLoader.INSTANCE.configDirectory, "nicetohave.json5").bufferedWriter().use { it.write(configString) }
        }
    }

    class Builder(val json : JsonObject) {
        private val mainCategory : ConfigCategory = ConfigCategory(isMain = true)
        private var currentCategory : ConfigCategory = mainCategory

        fun newCategory(key: String, comment : String): Builder {
            currentCategory = ConfigCategory(key, comment)
            mainCategory.addSubCategory(key, currentCategory)
            return this
        }

        fun endCategory(): Builder {
            currentCategory = mainCategory
            return this
        }

        fun addBool(key: String, comment: String, default : Boolean = true): Builder {
            currentCategory.addOption(key, ConfigOption(boolType, default, trueKey(key), "$comment [True/False]"))
            return this
        }

        fun addDouble(key: String, comment: String, default : Double): Builder {
            currentCategory.addOption(key, ConfigOption(doubleType, default, trueKey(key), "$comment [Any Real Number]"))
            return this
        }

        fun addRange(key: String, comment: String, default: Double, min : Double, max : Double): Builder {
            currentCategory.addOption(key, RangeConfigOption(default, min, max, trueKey(key), "$comment [Range: $min to $max]"))
            return this
        }

        fun addMetaInt(key: String, comment: String, value : Int): Builder {
            mainCategory.addOption(key, ConfigMetaData(intType, value, key, "$comment [Meta Data]"))
            return this
        }

        fun build(): ConfigCategory {
            mainCategory.deserialize(json)
            return mainCategory
        }

        private fun trueKey(key : String): String {
            if (currentCategory == mainCategory) {
                return key
            }
            return "${currentCategory.key}.$key"
        }
    }

     fun classToType(clazz : Class<*>): String {
        return when (clazz) {
            boolType -> "boolean"
            doubleType -> "double"
            intType -> "double"
            else -> {
                clazz.toString()
            }
        }
    }
}