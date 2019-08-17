package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import java.lang.Exception

class ConfigCategory(val key : String? = null, val comment : String? = null, val isMain : Boolean = false) {

    private val optionsMap : HashMap<String, ConfigOption<*>> = HashMap()
    private val subCategoriesMap : HashMap<String, ConfigCategory> = HashMap()

    fun serialize(json: JsonObject): JsonObject {
        val selfObject = if (isMain) json else JsonObject()

        for (optionEntry in optionsMap) {
            optionEntry.value.serialize(selfObject)
        }

        for (subCategoryEntry in subCategoriesMap) {
            subCategoryEntry.value.serialize(selfObject)
        }

        if (!isMain) {
            if (key != null) {
                json.put(key, selfObject, comment)
            }
            else {
                NiceToHave.error("Error!")
            }
        }

        return json
    }

    fun deserialize(json : JsonObject) {
        for (entry in json) {
            if (entry.value is JsonPrimitive) {
                if (optionsMap[entry.key] is ConfigOption) {
                    val option= optionsMap[entry.key]!!
                    option.deserialize(entry.value as JsonPrimitive)
                }
                else {
                    NiceToHave.error("Attempted to load non-option `${entry.key}`")
                }
            }
            else if (entry.value is JsonObject) {
                if (subCategoriesMap.containsKey(entry.key)) {
                    val subCategory = subCategoriesMap[entry.key]!!
                    subCategory.deserialize(entry.value as JsonObject)
                }
                else {
                    NiceToHave.error("Attempted to load non-category `${entry.key}`.")
                }
            }
        }
    }

    fun addOption(optionKey : String, option: ConfigOption<*>) {
        optionsMap[optionKey] = option
    }

    fun addSubCategory(categoryKey : String, category: ConfigCategory) {
        subCategoriesMap[categoryKey] = category
    }

    fun <T : Any> getOption(keySequence: Sequence<String>, default : T, originalKey : String, optionType : Class<T>): T {
        val firstKey = keySequence.first()
        try {
            if (keySequence.last() == firstKey) {
                val option = optionsMap[firstKey]
                if (option != null) {
                    if (option.type == optionType) {
                        return option.value as T
                    }
                }
            }
            else {
                val subCategory = subCategoriesMap[firstKey]
                if (subCategory != null) {
                    return subCategory.getOption(keySequence.drop(1), default, originalKey, optionType)
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
        NiceToHave.error("Error while reading config option $originalKey. Will use default value of $default.")
        return default
    }
}