package io.github.redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks
import io.github.redstoneparadox.nicetohave.item.wrench.WrenchItem
import io.github.redstoneparadox.nicetohave.util.initializers.ItemsInitializer
import io.github.redstoneparadox.nicetohave.config.Config

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object NiceToHaveItems : ItemsInitializer() {

    var CHAIN_LINK : Item = register("chain_link", Item(Item.Settings().group(ItemGroup.MATERIALS)))
    var DYNAMITE : Item = register("dynamite", DynamiteItem())
    var WRENCH : Item = register("wrench", WrenchItem())
    var FERTILIZER : Item = register("fertilizer", BoneMealItem(Item.Settings().group(ItemGroup.MATERIALS)))

    //Food
    var SWEET_BERRY_JUICE : Item = register("sweet_berry_juice", DrinkItem(FoodComponents.JUICE))
    var APPLE_JUICE : Item = register("apple_juice", DrinkItem(FoodComponents.JUICE))

    //BlockItems.
    var GOLD_BUTTON : BlockItem? = registerBlockItem("gold_button", NiceToHaveBlocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))
    var ANALOG_REDSTONE_EMITTER: BlockItem? = registerBlockItem("analog_redstone_emitter", NiceToHaveBlocks.ANALOG_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE))
    //var CHAIN_LINK_FENCE: BlockItem? = registerBlockItem("chain_link_fence", Blocks.CHAIN_LINK_FENCE, Item.Settings().group(ItemGroup.DECORATIONS))

    var PAINT_BRUSH: PaintbrushItem = register("paintbrush", PaintbrushItem(Item.Settings().group(ItemGroup.TOOLS)))

    var OAK_POLE: BlockItem = registerWoodPoleItem("oak", NiceToHaveBlocks.OAK_POLE)
    var SPRUCE_POLE: BlockItem = registerWoodPoleItem("spruce", NiceToHaveBlocks.SPRUCE_POLE)
    var BIRCH_POLE: BlockItem = registerWoodPoleItem("birch", NiceToHaveBlocks.BIRCH_POLE)
    var JUNGLE_POLE: BlockItem = registerWoodPoleItem("jungle", NiceToHaveBlocks.JUNGLE_POLE)
    var ACACIA_POLE: BlockItem = registerWoodPoleItem("acacia", NiceToHaveBlocks.ACACIA_POLE)
    var DARK_OAK_POLE: BlockItem = registerWoodPoleItem("dark_oak", NiceToHaveBlocks.DARK_OAK_POLE)
    var WARPED_POLE: BlockItem = registerWoodPoleItem("warped", NiceToHaveBlocks.WARPED_POLE)
    var CRIMSON_POLE: BlockItem = registerWoodPoleItem("crimson", NiceToHaveBlocks.CRIMSON_POLE)

    var STRIPPED_OAK_POLE: BlockItem = registerWoodPoleItem("stripped_oak", NiceToHaveBlocks.STRIPPED_OAK_POLE)
    var STRIPPED_SPRUCE_POLE: BlockItem = registerWoodPoleItem("stripped_spruce", NiceToHaveBlocks.STRIPPED_SPRUCE_POLE)
    var STRIPPED_BIRCH_POLE: BlockItem = registerWoodPoleItem("stripped_birch", NiceToHaveBlocks.STRIPPED_BIRCH_POLE)
    var STRIPPED_JUNGLE_POLE: BlockItem = registerWoodPoleItem("stripped_jungle", NiceToHaveBlocks.STRIPPED_JUNGLE_POLE)
    var STRIPPED_ACACIA_POLE: BlockItem = registerWoodPoleItem("stripped_acacia", NiceToHaveBlocks.STRIPPED_ACACIA_POLE)
    var STRIPPED_DARK_OAK_POLE: BlockItem = registerWoodPoleItem("stripped_dark_oak", NiceToHaveBlocks.STRIPPED_DARK_OAK_POLE)
    var STRIPPED_WARPED_POLE: BlockItem = registerWoodPoleItem("stripped_warped", NiceToHaveBlocks.STRIPPED_WARPED_POLE)
    var STRIPPED_CRIMSON_POLE: BlockItem = registerWoodPoleItem("stripped_crimson", NiceToHaveBlocks.STRIPPED_CRIMSON_POLE)

    fun initItems() {
        registerFuelForEach(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), 300)
        registerFuelForEach(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), 300)
    }

}