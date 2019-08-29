package redstoneparadox.nicetohave.util.newconfig

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import kotlin.reflect.KProperty

open class NewConfigOption<T : Any>(val type : Class<T>, var value : T, val key : String, val comment : String, val parentCategory: NewConfigCategory) {

    operator fun getValue(thisRef : Any?, property: KProperty<*>): T {
        return value
    }

    open operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }

    fun serialize(parentObject : JsonObject): JsonObject {
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
        NiceToHave.error("Expected type of ${NewConfig.stringifyType(type)} for config option ${getFullKey()} but instead found type of ${NewConfig.stringifyType(anyClass)}. Will use value of `$value` instead.")
        return this.value;
    }

    fun getFullKey(): String {
        return "${parentCategory.getFullKey()}.$key"
    }
}