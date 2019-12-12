package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import redstoneparadox.nicetohave.util.config.OldConfig.boolType
import redstoneparadox.nicetohave.util.config.OldConfig.doubleType
import redstoneparadox.nicetohave.util.config.OldConfig.longType

@Deprecated("use new config system.")
open class ConfigCategory(val key : String = "", val comment : String = "") {
    protected var wasInitialized = false;
    private var parentCategory: ConfigCategory? = null

    private val optionsMap : HashMap<String, ConfigOption<*>> = HashMap()
    private val subCategoriesMap : HashMap<String, ConfigCategory> = HashMap()

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
                    NiceToHave.error("Attempted to load non-existent option `${getKeyPrefix()}${entry.key}`")
                }
            }
            else if (entry.value is JsonObject) {
                if (subCategoriesMap.containsKey(entry.key)) {
                    subCategoriesMap[entry.key]!!.deserialize(entry.value as JsonObject)
                }
                else {
                    NiceToHave.error("Attempted to load non-existent category `${getKeyPrefix()}${entry.key}`.")
                }
            }
        }
    }

    fun getConfigRoot(): ConfigCategory = parentCategory?.getConfigRoot() ?: this

    protected fun addChildCategory(child : ConfigCategory, key: String) {
        subCategoriesMap[key] = child;
    }

    protected fun boolOption(default: Boolean, key: String, comment: String): ConfigOption<Boolean> {
        val option = ConfigOption(boolType, default, key, "$comment [Values: true/false]", getSelf())
        optionsMap[key] = option
        return option
    }

    protected fun doubleOption(default: Double, key: String, comment: String): ConfigOption<Double> {
        val option = ConfigOption(doubleType, default, key, "$comment [Values: any number]", getSelf())
        optionsMap[key] = option
        return option
    }

    protected fun longOption(default: Long, key: String, comment: String): ConfigOption<Long> {
        val option = ConfigOption(longType, default, key, "$comment [Values: any whole number]", getSelf())
        optionsMap[key] = option
        return option
    }

    protected fun rangeOption(default: Double, min : Double, max : Double, key: String, comment: String): RangeConfigOption {
        val option = RangeConfigOption(default, min, max, key, "$comment [Values: $min to $max]", getSelf())
        optionsMap[key] = option
        return option
    }

    protected fun metaLongOption(value : Long, key: String, comment: String): ConfigMetaData<Long> {
        val option = ConfigMetaData(longType, value, key, "$comment [Meta Data]", getSelf())
        optionsMap[key] = option
        return option
    }

    protected fun deprecatedOption(key: String, movedKey: String): DeprecatedConfigOption {
        val option = DeprecatedConfigOption(key, getSelf(), movedKey)
        optionsMap[key] = option
        return option
    }

    fun getFullKey(): String {
        if (parentCategory != null && parentCategory!!.key.isNotEmpty()) {
            return "${parentCategory!!.getFullKey()}.$key"
        }
        return key
    }

    fun getKeyPrefix(): String {
        if (key.isEmpty()) {
            return ""
        }
        if (parentCategory != null) {
            return "${parentCategory!!.getKeyPrefix()}$key."
        }

        return "$key."
    }

    internal fun setParent(parentCategory: ConfigCategory) {
        this.parentCategory = parentCategory;
        parentCategory.addChildCategory(getSelf(), key)
    }

    fun getSelf(): ConfigCategory {
        return this
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

    fun getOptionObject(keySequence: Sequence<String>, originalKey: String): ConfigOption<*>? {
        val firstKey = keySequence.first()
        try {
            if (keySequence.last() == firstKey) {
                val option = optionsMap[firstKey]
                if (option != null) {
                    return option
                }
            }
            else {
                val subCategory = subCategoriesMap[firstKey]
                if (subCategory != null) {
                    return subCategory.getOptionObject(keySequence.drop(1), originalKey)
                }
            }
        } catch (e: Exception) {
            NiceToHave.error("Config option $originalKey could not be found")
        }
        return null
    }
}