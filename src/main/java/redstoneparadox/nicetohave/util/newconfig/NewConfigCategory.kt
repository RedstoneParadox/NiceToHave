package redstoneparadox.nicetohave.util.newconfig

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import redstoneparadox.nicetohave.util.newconfig.NewConfig.boolType
import redstoneparadox.nicetohave.util.newconfig.NewConfig.doubleType

open class NewConfigCategory(val key : String = "", val comment : String = "", val parentCategory: NewConfigCategory? = null) {

    private val optionsMap : HashMap<String, NewConfigOption<*>> = HashMap()
    private val subCategoriesMap : HashMap<String, NewConfigCategory> = HashMap()

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

        if (parentObject == null) {
            return selfObject;
        }

        parentObject.put(key, selfObject, comment)
        return parentObject
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

    protected fun addChildCategory(child : NewConfigCategory, key: String) {
        subCategoriesMap[key] = child;
    }

    protected fun boolOption(default: Boolean, key: String, comment: String): NewConfigOption<Boolean> {
        val option = NewConfigOption(boolType, default, key, comment, this)
        optionsMap[key] = option
        return option
    }

    protected fun doubleOption(default: Double, key: String, comment: String): NewConfigOption<Double> {
        val option = NewConfigOption(doubleType, default, key, comment, this)
        optionsMap[key] = option
        return option
    }

    protected fun rangeOption(default: Double, min : Double, max : Double, key: String, comment: String): NewRangeConfigOption {
        val option = NewRangeConfigOption(default, min, max, key, comment, this)
        optionsMap[key] = option
        return option
    }

    fun getFullKey(): String {
        if (parentCategory != null) {
            return "${parentCategory.getFullKey()}.$key"
        }
        return key
    }

    fun getSelf(): NewConfigCategory {
        return this
    }
}