package io.github.redstoneparadox.nicetohave.data

import io.github.redstoneparadox.nicetohave.config.Config
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier

object DataManager {
    private var conditionsMap: MutableMap<Identifier, () -> Boolean> = mutableMapOf()

    private val POWERED_RAIL = Identifier("minecraft:recipes/powered_rail.json")
    private val DETECTOR_RAIL = Identifier("minecraft:recipes/detector_rail.json")
    private val ACTIVATOR_RAIL = Identifier("minecraft:recipes/activator_rail.json")

    private val LOADER: FabricLoader by lazy { FabricLoader.getInstance() }

    private fun addCondition(id: Identifier, predicate: () -> Boolean) {
        conditionsMap[id] = predicate
    }

    private fun addConditions(ids: Array<Identifier>, predicate: () -> Boolean) {
        for (identifier in ids) {
            addCondition(identifier, predicate)
        }
    }

    fun getCondition(id: Identifier): (() -> Boolean)? {
        return conditionsMap[id]
    }

    fun initConditions() {
        val railPredicate = { Config.Recipes.increasedRailOutput }
        addConditions(arrayOf(POWERED_RAIL, DETECTOR_RAIL, ACTIVATOR_RAIL), railPredicate)

        woodConditions(arrayOf("oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "warped", "crimson"))
        woodConditions(arrayOf("redwood", "hemlock", "rubber", "cypress", "willow", "japanese_maple", "rainbow_eucalyptus", "sakura"), "terrestria")
        woodConditions(arrayOf("fir"), "traverse")

        glueSlabConditions(arrayOf("bricks", "stone_bricks", "prismarine_bricks", "nether_bricks", "end_stone_bricks", "mossy_stone_bricks", "cobblestone", "mossy_cobblestone", "stone", "smooth_stone", "sandstone", "smooth_sandstone", "cut_sandstone", "red_sandstone", "smooth_red_sandstone", "smooth_quartz", "granite", "polished_granite", "andesite", "polished_andesite", "polished_diorite", "diorite"))
        glueSlabConditions(arrayOf("basalt_bricks", "mossy_basalt_bricks", "basalt", "smooth_basalt", "basalt_cobblestone", "mossy_basalt_cobblestone"), "terrestria")
    }

    private fun woodConditions(names: Array<String>, requiredMod: String? = null) {
        for (wood in names) {
            poleCondition(wood, requiredMod)
            poleCondition("stripped_$wood", requiredMod)
            glueSlabCondition(wood)
        }
    }

    private fun glueSlabConditions(names: Array<String>, requiredMod: String? = null) {
        for (name in names) {
            glueSlabCondition(name.removeSuffix("s"), requiredMod)
        }
    }

    private fun poleCondition(prefix: String, requiredMod: String? = null) {
        val poleRecipeID = Identifier("nicetohave:recipes/${prefix}_pole.json")
        val poleLootTableID = Identifier("nicetohave:loot_tables/blocks/${prefix}_pole.json")

        val cond = if (requiredMod != null) {
            { Config.Blocks.poles && LOADER.isModLoaded(requiredMod) }
        }
        else {
            { Config.Blocks.poles }
        }

        addCondition(poleRecipeID, cond)
        addCondition(poleLootTableID, cond)
    }

    private fun glueSlabCondition(name: String, requiredMod: String? = null) {
        val glueSlabRecipeID = Identifier("nicetohave:recipes/glue_${name}_slab.json")

        val cond = if (requiredMod != null) {
            { Config.Recipes.glueSlabs && LOADER.isModLoaded(requiredMod) }
        }
        else {
            { Config.Recipes.glueSlabs }
        }

        addCondition(glueSlabRecipeID, cond)
    }
}