package redstoneparadox.nicetohave.util.oldconfig

import blue.endless.jankson.JsonPrimitive

class OldConfigMetaData<T : Any>(type: Class<T>, value: T, key: String, comment: String) : OldConfigOption<T>(type, value, key, comment) {

    override fun deserialize(jsonPrimitive: JsonPrimitive) {
        return
    }
}