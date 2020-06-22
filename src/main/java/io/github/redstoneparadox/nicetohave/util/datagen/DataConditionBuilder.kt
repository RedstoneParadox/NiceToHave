package io.github.redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import kotlin.collections.HashMap

class DataConditionBuilder {
    val conditions : HashMap<String, Array<out Any>> = HashMap()
    val objectConditions: HashMap<String, Array<out Pair<String, Any>>> = HashMap()

    fun addCondition(condition : String, vararg values: Any): DataConditionBuilder {
        conditions[condition] = values
        return this
    }

    fun addObjectCondition(condition : String, vararg values: Pair<String, Any>): DataConditionBuilder {
        objectConditions[condition] = values
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

        for (entry in objectConditions) {
            val conditionObject = JsonObject()
            val innerObject = JsonObject()
            for (pair in entry.value) {
                innerObject[pair.first] = JsonPrimitive(pair.second)
            }
            conditionObject[entry.key] = innerObject
            conditionsArray.add(conditionObject)
        }

        val rootObject = JsonObject()
        rootObject["when"] = conditionsArray
        return rootObject
    }
}