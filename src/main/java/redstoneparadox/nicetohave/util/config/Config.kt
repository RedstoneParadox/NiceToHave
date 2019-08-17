package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject

object Config {

    private val mainCategory : ConfigCategory

    //Types
    val boolType = Boolean::class.javaObjectType
    val doubleType = Double::class.javaObjectType
    val intType = Int::class.javaObjectType

    init {
        val placeholder = JsonObject()
        mainCategory = Builder(placeholder)
                .addMetaInt("config_version", "Config format version.", 1)
                .newCategory("items", "Enable/Disable items.")
                .addBool("chain_link", "Set to false to disable chain-links.")
                .addBool("dynamite", "Set to false to disable dynamite.")
                .addBool("wrench", "Set to false to disable the wrench.")
                .addBool("fertilizer", "Set to false to disable fertilizer.")
                .addBool("sweet_berry_juice", "Set to false to disable sweet berry juice")
                .addBool("apple_juice", "Set to false to disable apple juice")
                .endCategory()
                .newCategory("blocks", "Enable/Disable blocks.")
                .addBool("gold_button", "Set to false to disable gold buttons.")
                .addBool("analog_redstone_emitter", "Set to false to disable analog redstone emitters.")
                //.addBool("chain_link_fence", "Set to false to disable chain-link fences.")
                .addBool("trimmed_vine", "Set to false to disable trimmed-vines.")
                .addBool("poles", "Set to false to disable pole blocks.")
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

    class Builder(val json : JsonObject) {
        private val mainCategory : ConfigCategory = ConfigCategory(isMain = true)
        private var currentCategory : ConfigCategory = mainCategory

        fun newCategory(key: String, comment : String): Builder {
            currentCategory = ConfigCategory(key, comment)
            return this
        }

        fun endCategory(): Builder {
            if (mainCategory != currentCategory) {
                mainCategory.addSubCategory(currentCategory.key!!, currentCategory)
                currentCategory = mainCategory
            }
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
            mainCategory.addOption(key, ConfigMetaData(intType, value, trueKey(key), "$comment [Meta Data]"))
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