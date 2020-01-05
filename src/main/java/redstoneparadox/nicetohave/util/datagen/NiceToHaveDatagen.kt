package redstoneparadox.nicetohave.util.datagen

import net.minecraft.util.DyeColor

fun main() {
    /*
    for (prefix in arrayOf("oak", "spruce", "birch", "jungle", "acacia", "dark_oak")) {
        CraftingRecipeBuilder.woodSlabGlueRecipe(prefix)
        CraftingRecipeBuilder.generatePoleRecipe(prefix)
        LootTableBuilder.generatePoleDrop("${prefix}_pole")
        BasicModelBuilder.createPoleBlockModel(prefix)
        BasicModelBuilder.createPoleItemModel(prefix)
        VariantBlockStateBuilder.generatePoleBlockState(prefix)

        val strippedPrefix = "stripped_$prefix"
        CraftingRecipeBuilder.generatePoleRecipe(strippedPrefix)
        LootTableBuilder.generatePoleDrop("${strippedPrefix}_pole")
        BasicModelBuilder.createPoleBlockModel(strippedPrefix)
        BasicModelBuilder.createPoleItemModel(strippedPrefix)
        VariantBlockStateBuilder.generatePoleBlockState(strippedPrefix)
    }

    for (prefix in arrayOf("redwood", "hemlock", "rubber", "cypress", "willow", "japanese_maple", "rainbow_eucalyptus", "sakura")) {
        CraftingRecipeBuilder.woodSlabGlueRecipe(prefix, "terrestria")
        CraftingRecipeBuilder.generatePoleRecipe(prefix, "terrestria")
        LootTableBuilder.generatePoleDrop("${prefix}_pole", "terrestria")
        BasicModelBuilder.createPoleBlockModel(prefix, "terrestria")
        BasicModelBuilder.createPoleItemModel(prefix)
        VariantBlockStateBuilder.generatePoleBlockState(prefix)

        val strippedPrefix = "stripped_$prefix"
        CraftingRecipeBuilder.generatePoleRecipe(strippedPrefix)
        LootTableBuilder.generatePoleDrop("${strippedPrefix}_pole")
        BasicModelBuilder.createPoleBlockModel(strippedPrefix, "terrestria")
        BasicModelBuilder.createPoleItemModel(strippedPrefix)
        VariantBlockStateBuilder.generatePoleBlockState(strippedPrefix)
    }

    CraftingRecipeBuilder.woodSlabGlueRecipe("fir", "traverse")
    CraftingRecipeBuilder.generatePoleRecipe("fir", "traverse")
    LootTableBuilder.generatePoleDrop("fir_pole", "traverse")
    BasicModelBuilder.createPoleBlockModel("fir", "traverse")
    BasicModelBuilder.createPoleItemModel("fir")
    VariantBlockStateBuilder.generatePoleBlockState("fir")

    val strippedFir = "stripped_fir"
    CraftingRecipeBuilder.generatePoleRecipe(strippedFir)
    LootTableBuilder.generatePoleDrop("${strippedFir}_pole")
    BasicModelBuilder.createPoleBlockModel(strippedFir, "traverse")
    BasicModelBuilder.createPoleItemModel(strippedFir)
    VariantBlockStateBuilder.generatePoleBlockState(strippedFir)

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
     */

    val builder = BasicModelBuilder().setParent("item/generated")
    for (dye in DyeColor.values()) {
        builder
                .setType(BasicModelBuilder.ModelType.ITEM)
                .setID("${dye.asString()}_paintbrush")
                .addTexture("layer0", "nicetohave:item/${dye.asString()}_paintbrush")
                .save()
    }
}