package redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.Blocks
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.util.Config

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items {

    val CHAIN_LINK : Item = Item(Item.Settings().group(ItemGroup.MATERIALS))
    val DYNAMITE : Item = DynamiteItem(Item.Settings().group(ItemGroup.TOOLS))
    val WRENCH : Item = WrenchItem(Item.Settings().group(ItemGroup.TOOLS).maxCount(1))
    val FERTILIZER : Item = BoneMealItem(Item.Settings().group(ItemGroup.MATERIALS))

    //BlockItems.
    val GOLD_BUTTON : BlockItem = BlockItem(Blocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))
    val ANALOG_REDSTONE_EMITTER = BlockItem(Blocks.ANALOG_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE))
    val DIRT_GOLD_ORE = BlockItem(Blocks.DIRT_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val SAND_GOLD_ORE = BlockItem(Blocks.SAND_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val GRAVEL_GOLD_ORE = BlockItem(Blocks.GRAVEL_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val CHAIN_LINK_FENCE = BlockItem(Blocks.CHAIN_LINK_FENCE, Item.Settings().group(ItemGroup.DECORATIONS))

    val OAK_POLE : BlockItem = BlockItem(Blocks.OAK_POLE, Item.Settings().group(ItemGroup.DECORATIONS))
    val SPRUCE_POLE : BlockItem = BlockItem(Blocks.SPRUCE_POLE, Item.Settings().group(ItemGroup.DECORATIONS))
    val BIRCH_POLE : BlockItem = BlockItem(Blocks.BIRCH_POLE, Item.Settings().group(ItemGroup.DECORATIONS))
    val JUNGLE_POLE : BlockItem = BlockItem(Blocks.JUNGLE_POLE, Item.Settings().group(ItemGroup.DECORATIONS))
    val ACACIA_POLE : BlockItem = BlockItem(Blocks.ACACIA_POLE, Item.Settings().group(ItemGroup.DECORATIONS))
    val DARK_OAK_POLE : BlockItem = BlockItem(Blocks.DARK_OAK_POLE, Item.Settings().group(ItemGroup.DECORATIONS))

    fun registerItems() {
        register(CHAIN_LINK, "chain_link")
        register(DYNAMITE, "dynamite")
        register(WRENCH, "wrench")
        register(FERTILIZER, "fertilizer")
      
        registerBlockItem(GOLD_BUTTON, "gold_button")
        registerBlockItem(ANALOG_REDSTONE_EMITTER, "analog_redstone_emitter")
        registerBlockItem(DIRT_GOLD_ORE, "dirt_gold_ore", false)
        registerBlockItem(SAND_GOLD_ORE, "sand_gold_ore", false)
        registerBlockItem(GRAVEL_GOLD_ORE, "gravel_gold_ore", false)
        registerBlockItem(CHAIN_LINK_FENCE, "chain_link_fence")

        if (Config.getBlockOption("poles", Config.boolType, true)) {
            registerBlockItem(OAK_POLE, "oak_pole", false)
            registerBlockItem(SPRUCE_POLE, "spruce_pole", false)
            registerBlockItem(BIRCH_POLE, "birch_pole", false)
            registerBlockItem(JUNGLE_POLE, "jungle_pole", false)
            registerBlockItem(ACACIA_POLE, "acacia_pole", false)
            registerBlockItem(DARK_OAK_POLE, "dark_oak_pole", false)
        }
    }

    private fun register(item : Item, id : String, respectsConfig: Boolean = true) {
        if (!respectsConfig || Config.getItemOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }

    private fun registerBlockItem(item: BlockItem, id: String, respectsConfig : Boolean = true) {
        if (!respectsConfig || Config.getBlockOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }
}