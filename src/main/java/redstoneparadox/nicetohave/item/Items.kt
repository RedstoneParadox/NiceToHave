package redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import redstoneparadox.nicetohave.block.Blocks
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items : ItemsInitializer() {

    val CHAIN_LINK : Item? = register(Item(Item.Settings().group(ItemGroup.MATERIALS)), "chain_link", null)
    val DYNAMITE : Item? = register(DynamiteItem(Item.Settings().group(ItemGroup.TOOLS)), "dynamite", null)
    val WRENCH : Item? = register(WrenchItem(Item.Settings().group(ItemGroup.TOOLS).maxCount(1)), "wrench", null)
    val FERTILIZER : Item? = register(BoneMealItem(Item.Settings().group(ItemGroup.MATERIALS)), "fertilizer", null)

    //Food
    val SWEET_BERRY_JUICE : Item? = register(DrinkItem(Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.JUICE)), "sweet_berry_juice", null)
    val APPLE_JUICE : Item? = register(DrinkItem(Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.JUICE)), "apple_juice", null)

    //BlockItems.
    val GOLD_BUTTON : BlockItem? = registerBlockItem(BlockItem(Blocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE)), "gold_button", null)
    val ANALOG_REDSTONE_EMITTER: BlockItem? = registerBlockItem(BlockItem(Blocks.ANALOG_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE)), "analog_redstone_emitter", null)
    val DIRT_GOLD_ORE: BlockItem? = registerBlockItem(BlockItem(Blocks.DIRT_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "dirt_gold_ore", false)
    val SAND_GOLD_ORE: BlockItem? = registerBlockItem(BlockItem(Blocks.SAND_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "sand_gold_ore", false)
    val GRAVEL_GOLD_ORE: BlockItem? = registerBlockItem(BlockItem(Blocks.GRAVEL_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS)), "gravel_gold_ore", false)
    val CHAIN_LINK_FENCE: BlockItem? = registerBlockItem(BlockItem(Blocks.CHAIN_LINK_FENCE, Item.Settings().group(ItemGroup.DECORATIONS)), "chain_link_fence", null)

    val OAK_POLE : BlockItem? = registerPoleItem(Blocks.OAK_POLE, "oak")
    val SPRUCE_POLE : BlockItem? = registerPoleItem(Blocks.SPRUCE_POLE, "spruce")
    val BIRCH_POLE : BlockItem? = registerPoleItem(Blocks.BIRCH_POLE, "birch")
    val JUNGLE_POLE : BlockItem? = registerPoleItem(Blocks.JUNGLE_POLE, "jungle")
    val ACACIA_POLE : BlockItem? = registerPoleItem(Blocks.ACACIA_POLE, "acacia")
    val DARK_OAK_POLE : BlockItem? = registerPoleItem(Blocks.DARK_OAK_POLE, "dark_oak")

    val STRIPPED_OAK_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_OAK_POLE, "stripped_oak")
    val STRIPPED_SPRUCE_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_SPRUCE_POLE, "stripped_spruce")
    val STRIPPED_BIRCH_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_BIRCH_POLE, "stripped_birch")
    val STRIPPED_JUNGLE_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_JUNGLE_POLE, "stripped_jungle")
    val STRIPPED_ACACIA_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_ACACIA_POLE, "stripped_acacia")
    val STRIPPED_DARK_OAK_POLE : BlockItem? = registerPoleItem(Blocks.STRIPPED_DARK_OAK_POLE, "stripped_dark_oak")

    fun initItems() {
        if (Config.getBool("blocks.poles")) {
            registerFuelForEach(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), 300)
            registerFuelForEach(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), 300)
        }
    }

}