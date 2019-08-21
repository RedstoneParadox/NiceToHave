package redstoneparadox.nicetohave.item

import com.mojang.datafixers.kinds.App
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
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

    val OAK_POLE : BlockItem? = registerBlockItem(Blocks.OAK_POLE?.createBlockItem(), "oak_pole", "blocks.poles")
    val SPRUCE_POLE : BlockItem? = registerBlockItem(Blocks.SPRUCE_POLE?.createBlockItem(), "spruce_pole", "blocks.poles")
    val BIRCH_POLE : BlockItem? = registerBlockItem(Blocks.BIRCH_POLE?.createBlockItem(), "birch_pole", "blocks.poles")
    val JUNGLE_POLE : BlockItem? = registerBlockItem(Blocks.JUNGLE_POLE?.createBlockItem(), "jungle_pole", "blocks.poles")
    val ACACIA_POLE : BlockItem? = registerBlockItem(Blocks.ACACIA_POLE?.createBlockItem(), "acacia_pole", "blocks.poles")
    val DARK_OAK_POLE : BlockItem? = registerBlockItem(Blocks.DARK_OAK_POLE?.createBlockItem(), "dark_oak_pole", "blocks.poles")

    fun initItems() {
        if (Config.getBool("blocks.poles")) {
            registerFuelForEach(arrayOf(Items.OAK_POLE, Items.SPRUCE_POLE, Items.BIRCH_POLE, Items.JUNGLE_POLE, Items.ACACIA_POLE, Items.DARK_OAK_POLE), 300)
        }
    }

}