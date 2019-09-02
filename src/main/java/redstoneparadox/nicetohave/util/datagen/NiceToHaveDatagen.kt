package redstoneparadox.nicetohave.util.datagen

import net.fabricmc.loader.api.FabricLoader

object NiceToHaveDatagen {

    fun generateData() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            CraftingRecipeBuilder.woodSlabGlueRecipe("oak")
            CraftingRecipeBuilder.woodSlabGlueRecipe("spruce")
            CraftingRecipeBuilder.woodSlabGlueRecipe("birch")
            CraftingRecipeBuilder.woodSlabGlueRecipe("jungle")
            CraftingRecipeBuilder.woodSlabGlueRecipe("acacia")
            CraftingRecipeBuilder.woodSlabGlueRecipe("dark_oak")

            for (prefix in arrayOf("redwood", "hemlock", "rubber", "cypress", "willow", "japanese_maple", "rainbow_eucalyptus", "sakura")) {
                CraftingRecipeBuilder.woodSlabGlueRecipe(prefix, "terrestria")
            }

            CraftingRecipeBuilder.woodSlabGlueRecipe("fir", "traverse")

            for (block in arrayOf("bricks", "stone_bricks", "prismarine_bricks", "nether_bricks", "end_stone_bricks", "mossy_stone_bricks")) {
                CraftingRecipeBuilder.regularSlabGlueRecipe(block, true)
            }

            for (block in arrayOf("cobblestone", "mossy_cobblestone", "stone", "smooth_stone", "sandstone", "smooth_sandstone", "cut_sandstone", "red_sandstone", "smooth_red_sandstone", "smooth_quartz", "granite", "polished_granite", "andesite", "polished_andesite", "polished_diorite", "diorite")) {
                CraftingRecipeBuilder.regularSlabGlueRecipe(block)
            }

            CraftingRecipeBuilder.regularSlabGlueRecipe("basalt_bricks", true, "terrestria")
            CraftingRecipeBuilder.regularSlabGlueRecipe("mossy_basalt_bricks", true, "terrestria")

            for (basalt in arrayOf("basalt", "smooth_basalt", "basalt_cobblestone", "mossy_basalt_cobblestone")) {
                CraftingRecipeBuilder.regularSlabGlueRecipe(basalt, mod = "terrestria")
            }
        }
    }
}