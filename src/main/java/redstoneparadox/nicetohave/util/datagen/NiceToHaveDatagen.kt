package redstoneparadox.nicetohave.util.datagen

fun main() {
    for (prefix in arrayOf("oak", "spruce", "birch", "jungle", "acacia", "dark_oak")) {
        CraftingRecipeBuilder.woodSlabGlueRecipe(prefix)
        CraftingRecipeBuilder.generatePoleRecipe(prefix)
        LootTableBuilder.generatePoleDrop("${prefix}_pole")
        BasicModelBuilder.createPoleBlockModel(prefix)
        BasicModelBuilder.createPoleItemModel(prefix)
        VariantBlockStateBuilder.generatePoleBlockState(prefix)
    }

    for (prefix in arrayOf("redwood", "hemlock", "rubber", "cypress", "willow", "japanese_maple", "rainbow_eucalyptus", "sakura")) {
        CraftingRecipeBuilder.woodSlabGlueRecipe(prefix, "terrestria")
        CraftingRecipeBuilder.generatePoleRecipe(prefix, "terrestria")
        LootTableBuilder.generatePoleDrop("${prefix}_pole", "terrestria")
        BasicModelBuilder.createPoleBlockModel(prefix, "terrestria")
        BasicModelBuilder.createPoleItemModel(prefix)
        VariantBlockStateBuilder.generatePoleBlockState(prefix)
    }

    CraftingRecipeBuilder.woodSlabGlueRecipe("fir", "traverse")
    CraftingRecipeBuilder.generatePoleRecipe("fir", "traverse")
    LootTableBuilder.generatePoleDrop("fir_pole", "traverse")
    BasicModelBuilder.createPoleBlockModel("fir")
    BasicModelBuilder.createPoleItemModel("fir")
    VariantBlockStateBuilder.generatePoleBlockState("fir")

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