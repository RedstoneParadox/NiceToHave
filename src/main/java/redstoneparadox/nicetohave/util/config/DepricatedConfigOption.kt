package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import kotlin.reflect.KProperty

class DeprecatedConfigOption(key: String, parentCategory: ConfigCategory, private val movedOption: String) : ConfigOption<Any>(Any::class.java, Any(), key, "", parentCategory) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): Any {
        NiceToHave.error("Attempted to access deprecated config option ${getFullKey()}. Here's a regular object instead.")
        return Any()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Any) {
        NiceToHave.error("Attempted to set value of deprecated config option ${getFullKey()}.")
    }

    override fun serialize(parentObject: JsonObject): JsonObject {
        return parentObject
    }

    override fun deserialize(primitive: JsonPrimitive) {
        val target = parentCategory.getConfigRoot().getOptionObject(movedOption.splitToSequence("."), movedOption)
        target?.deserialize(primitive)
    }
}