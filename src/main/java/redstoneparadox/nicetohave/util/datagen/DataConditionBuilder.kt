package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive

class DataConditionBuilder {
    val conditions : HashMap<String, Array<out Any>> = HashMap()

    fun addCondition(condition : String, vararg values : Any): DataConditionBuilder {
        conditions[condition] = values
        return this
    }

    fun clear(): DataConditionBuilder {
        conditions.clear()
        return this
    }

    fun build(): JsonObject {
        val conditionsArray = JsonArray()
        for (pair in conditions) {
            val conditionObject = JsonObject()
            if (pair.value.size == 1) {
                conditionObject[pair.key] = JsonPrimitive(pair.value.first())
            }
            else {
                val evalArray = JsonArray()
                for (any in pair.value) {
                    val primitive = JsonPrimitive(pair.value)
                    evalArray.add(primitive)
                }
                conditionObject[pair.key] = evalArray
            }
            conditionsArray.add(conditionObject)
        }

        val rootObject = JsonObject()
        rootObject["when"] = conditionsArray
        return rootObject
    }
}