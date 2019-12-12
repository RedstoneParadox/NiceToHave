package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonPrimitive

@Deprecated("use new config system.")
class ConfigMetaData<T : Any>(type: Class<T>, value: T, key: String, comment: String, parentCategory: ConfigCategory) : ConfigOption<T>(type, value, key, comment, parentCategory) {

    override fun deserialize(primitive: JsonPrimitive) {

    }
}