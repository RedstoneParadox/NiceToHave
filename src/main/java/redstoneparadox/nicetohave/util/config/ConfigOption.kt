package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import kotlin.reflect.KProperty

@Deprecated("use new config system.")
open class ConfigOption<T : Any>(val type : Class<T>, var value : T, val key : String, private val comment : String, protected val parentCategory: ConfigCategory) {

    open operator fun getValue(thisRef : Any?, property: KProperty<*>): T {
        return value
    }

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    open fun serialize(parentObject : JsonObject): JsonObject {
        parentObject.put(key, JsonPrimitive(value), comment)
        return parentObject
    }

    open fun deserialize(primitive : JsonPrimitive) {
        value = unwrapPrimitive(primitive)
    }

    fun unwrapPrimitive(primitive: JsonPrimitive): T {
        val any = primitive.value
        val anyClass = any::class.java
        if (anyClass == type) {
            return any as T
        }
        NiceToHave.error("Expected type of `${typeString()}` for config option ${getFullKey()} but instead found type of `${OldConfig.stringifyType(anyClass)}`. Will use `${typeString()} value of `$value` instead.")
        return this.value;
    }

    fun getFullKey(): String = "${parentCategory.getKeyPrefix()}$key"

    fun typeString(): String = OldConfig.stringifyType(type)
}