package io.github.redstoneparadox.nicetohave.data

import io.github.redstoneparadox.nicetohave.config.Config
import net.minecraft.util.Identifier

object DataManager {
    private var conditionsMap: MutableMap<Identifier, () -> Boolean> = mutableMapOf()

    private val POWERED_RAIL = Identifier("minecraft:recipes/powered_rail.json")
    private val DETECTOR_RAIL = Identifier("minecraft:recipes/detector_rail.json")
    private val ACTIVATOR_RAIL = Identifier("minecraft:recipes/activator_rail.json")

    private fun addCondition(id: Identifier, predicate: () -> Boolean) {
        conditionsMap[id] = predicate
    }

    private fun addConditions(ids: Array<Identifier>, predicate: () -> Boolean) {
        for (identifier in ids) {
            addCondition(identifier, predicate)
        }
    }

    public fun getCondition(id: Identifier): (() -> Boolean)? {
        return conditionsMap[id]
    }

    public fun initConditions() {
        val railPredicate = { Config.Recipes.increasedRailOutput }
        addConditions(arrayOf(POWERED_RAIL, DETECTOR_RAIL, ACTIVATOR_RAIL), railPredicate)

        for (vanillaLog in arrayOf("acacia")) {
            val recipeId = Identifier("nicetohave:recipes/${vanillaLog}_pole.json")
            addCondition(recipeId) { Config.Blocks.poles }
        }
    }
}