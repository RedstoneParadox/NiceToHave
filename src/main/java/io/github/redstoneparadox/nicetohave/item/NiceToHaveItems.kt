package io.github.redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks
import io.github.redstoneparadox.nicetohave.item.wrench.WrenchItem
import io.github.redstoneparadox.nicetohave.util.initializers.ItemsInitializer

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object NiceToHaveItems : ItemsInitializer() {

    var CHAIN_LINK : Item = Item(Item.Settings().group(ItemGroup.MATERIALS))
    var DYNAMITE : Item = DynamiteItem()
    var WRENCH : Item = WrenchItem()
    var PAINT_BRUSH: Item = PaintbrushItem(Item.Settings().group(ItemGroup.TOOLS))
    var FERTILIZER : Item = BoneMealItem(Item.Settings().group(ItemGroup.MATERIALS))

    //Food
    var SWEET_BERRY_JUICE : Item = DrinkItem(FoodComponents.JUICE)
    var APPLE_JUICE : Item = DrinkItem(FoodComponents.JUICE)

    //BlockItems.
    var GOLD_BUTTON : BlockItem = BlockItem(NiceToHaveBlocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))
    var ANALOG_REDSTONE_EMITTER: BlockItem = BlockItem(NiceToHaveBlocks.ANALOG_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE))

    var OAK_POLE: BlockItem = poleItem(NiceToHaveBlocks.OAK_POLE)
    var SPRUCE_POLE: BlockItem = poleItem(NiceToHaveBlocks.SPRUCE_POLE)
    var BIRCH_POLE: BlockItem = poleItem(NiceToHaveBlocks.BIRCH_POLE)
    var JUNGLE_POLE: BlockItem = poleItem(NiceToHaveBlocks.JUNGLE_POLE)
    var ACACIA_POLE: BlockItem = poleItem(NiceToHaveBlocks.ACACIA_POLE)
    var DARK_OAK_POLE: BlockItem = poleItem(NiceToHaveBlocks.DARK_OAK_POLE)
    var WARPED_POLE: BlockItem = poleItem(NiceToHaveBlocks.WARPED_POLE)
    var CRIMSON_POLE: BlockItem = poleItem(NiceToHaveBlocks.CRIMSON_POLE)

    var STRIPPED_OAK_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_OAK_POLE)
    var STRIPPED_SPRUCE_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_SPRUCE_POLE)
    var STRIPPED_BIRCH_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_BIRCH_POLE)
    var STRIPPED_JUNGLE_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_JUNGLE_POLE)
    var STRIPPED_ACACIA_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_ACACIA_POLE)
    var STRIPPED_DARK_OAK_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_DARK_OAK_POLE)
    var STRIPPED_WARPED_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_WARPED_POLE)
    var STRIPPED_CRIMSON_POLE: BlockItem = poleItem(NiceToHaveBlocks.STRIPPED_CRIMSON_POLE)

    fun initItems() {
        register("chain_link", CHAIN_LINK)
        register("dynamite", DYNAMITE)
        register("wrench", WRENCH)
        register("paint_brush", PAINT_BRUSH)
        register("fertilizer", FERTILIZER)

        register("sweet_berry_juice", SWEET_BERRY_JUICE)
        register("apple_juice", APPLE_JUICE)

        register("gold_button", GOLD_BUTTON)
        register("analog_redstone_emitter", ANALOG_REDSTONE_EMITTER)

        register("oak_pole", OAK_POLE)
        register("spruce_pole", SPRUCE_POLE)
        register("birch_pole", BIRCH_POLE)
        register("jungle_pole", JUNGLE_POLE)
        register("acacia_pole", ACACIA_POLE)
        register("dark_oak_pole", DARK_OAK_POLE)
        register("warped_pole", WARPED_POLE)
        register("crimson_pole", CRIMSON_POLE)

        register("stripped_oak_pole", STRIPPED_OAK_POLE)
        register("stripped_spruce_pole", STRIPPED_SPRUCE_POLE)
        register("stripped_birch_pole", STRIPPED_BIRCH_POLE)
        register("stripped_jungle_pole", STRIPPED_JUNGLE_POLE)
        register("stripped_acacia_pole", STRIPPED_ACACIA_POLE)
        register("stripped_dark_oak_pole", STRIPPED_DARK_OAK_POLE)
        register("stripped_warped_pole", STRIPPED_WARPED_POLE)
        register("stripped_crimson_pole", STRIPPED_CRIMSON_POLE)

        registerFuelForEach(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), 300)
        registerFuelForEach(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), 300)
    }

}