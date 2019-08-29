package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import redstoneparadox.nicetohave.util.config.Config.boolType
import redstoneparadox.nicetohave.util.config.Config.doubleType

open class ConfigCategory(val key : String = "", val comment : String = "", val parentCategory: ConfigCategory? = null) {

    private val optionsMap : HashMap<String, ConfigOption<*>> = HashMap()
    private val subCategoriesMap : HashMap<String, ConfigCategory> = HashMap()

    init {
        parentCategory?.addChildCategory(getSelf(), key)
    }

    fun serialize(parentObject: JsonObject? = null): JsonObject {
        val selfObject = JsonObject()

        for (optionEntry in optionsMap) {
            optionEntry.value.serialize(selfObject)
        }

        for (subCategoryEntry in subCategoriesMap) {
            subCategoryEntry.value.serialize(selfObject)
        }

        if (parentObject != null) {
            parentObject.put(key, selfObject, comment)
            return parentObject
        }

        return selfObject
    }

    fun deserialize(parentObject: JsonObject) {
        for (entry in parentObject) {
            if (entry.value is JsonPrimitive) {
                if (optionsMap.containsKey(entry.key)) {
                    optionsMap[entry.key]!!.deserialize(entry.value as JsonPrimitive)
                }
                else {
                    NiceToHave.error("Attempted to load non-option `${entry.key}`")
                }
            }
            else if (entry.value is JsonObject) {
                if (subCategoriesMap.containsKey(entry.key)) {
                    subCategoriesMap[entry.key]!!.deserialize(entry.value as JsonObject)
                }
                else {
                    NiceToHave.error("Attempted to load non-category `${entry.key}`.")
                }
            }
        }
    }

    protected fun addChildCategory(child : ConfigCategory, key: String) {
        subCategoriesMap[key] = child;
    }

    protected fun boolOption(default: Boolean, key: String, comment: String): ConfigOption<Boolean> {
        val option = ConfigOption(boolType, default, key, comment, this)
        optionsMap[key] = option
        return option
    }

    protected fun doubleOption(default: Double, key: String, comment: String): ConfigOption<Double> {
        val option = ConfigOption(doubleType, default, key, comment, this)
        optionsMap[key] = option
        return option
    }

    protected fun rangeOption(default: Double, min : Double, max : Double, key: String, comment: String): RangeConfigOption {
        val option = RangeConfigOption(default, min, max, key, comment, this)
        optionsMap[key] = option
        return option
    }

    fun getFullKey(): String {
        if (parentCategory != null) {
            return "${parentCategory.getFullKey()}.$key"
        }
        return key
    }

    fun getSelf(): ConfigCategory {
        return this
    }
}