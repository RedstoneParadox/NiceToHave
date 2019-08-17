package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave

open class ConfigOption<T : Any>(val type : Class<T>, var value : T, val key : String, val comment : String) {

    fun serialize(json : JsonObject): JsonObject {
        json.put(key, JsonPrimitive(value), comment)
        return json
    }

    open fun deserialize(jsonPrimitive: JsonPrimitive) {
        value = primitiveToValue(jsonPrimitive.value)
    }

    protected fun primitiveToValue(any : Any): T {
        if (any::class.java != type) {
            NiceToHave.error("Expected type of `${Config.classToType(type)}` for config option `$key` but instead found type of `${Config.classToType(any::class.java)}`. Will use default value of `$value`.")
            return value
        }
        return any as T
    }

}