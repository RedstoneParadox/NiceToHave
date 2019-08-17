package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonPrimitive

class ConfigMetaData<T : Any>(type: Class<T>, value: T, key: String, comment: String) : ConfigOption<T>(type, value, key, comment) {

    override fun deserialize(jsonPrimitive: JsonPrimitive) {
        return
    }
}