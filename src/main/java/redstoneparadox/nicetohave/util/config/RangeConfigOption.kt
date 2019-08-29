package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.JsonPrimitive
import redstoneparadox.nicetohave.NiceToHave
import kotlin.reflect.KProperty

class RangeConfigOption(value : Double, val min : Double, val max : Double, key: String, comment: String, parentCategory: ConfigCategory) : ConfigOption<Double>(Double::class.javaObjectType, value, key, comment, parentCategory) {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        setWithinRange(value)
    }

    override fun deserialize(primitive: JsonPrimitive) {
        setWithinRange(unwrapPrimitive(primitive))
    }

    private fun setWithinRange(value: Double) {
        if (value > min && value < max) {
            this.value = value
            return
        }
        NiceToHave.error("Excepted value within range of $min to $max for config option ${getFullKey()}. Will use value of $value instead.")
    }
}