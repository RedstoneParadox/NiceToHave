package redstoneparadox.nicetohave.util.oldconfig

import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave

class RangeOldConfigOption(value: Double, val min : Double, val max : Double, key: String, comment: String) : OldConfigOption<Double>(OldConfig.doubleType, value, key, comment) {

    override fun deserialize(jsonPrimitive: JsonPrimitive) {
        val primitiveValue = primitiveToValue(jsonPrimitive.value)
        if (primitiveValue !in min..max) {
            NiceToHave.error("Value `$primitiveValue` for option `$key` is not within the specified range of $min to $max. The default value of $value will be used instead.")
            return
        }
        value = primitiveValue
    }
}