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
        }
    }
}