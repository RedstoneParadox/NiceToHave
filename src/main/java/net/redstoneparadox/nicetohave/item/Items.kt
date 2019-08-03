package net.redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.item.wrench.WrenchItem
import net.redstoneparadox.nicetohave.util.Config

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items {

    val CHAIN_LINK : Item = Item(Item.Settings().group(ItemGroup.MATERIALS))
    val DYNAMITE : Item = DynamiteItem(Item.Settings().group(ItemGroup.TOOLS))
    val WRENCH : Item = WrenchItem(Item.Settings().group(ItemGroup.TOOLS))

    //BlockItems.
    val GOLD_BUTTON : BlockItem = BlockItem(Blocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))
    val VARIABLE_REDSTONE_EMITTER = BlockItem(Blocks.VARIABLE_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE))
    val DIRT_GOLD_ORE = BlockItem(Blocks.DIRT_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val SAND_GOLD_ORE = BlockItem(Blocks.SAND_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val GRAVEL_GOLD_ORE = BlockItem(Blocks.GRAVEL_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val CHAIN_LINK_FENCE = BlockItem(Blocks.CHAIN_LINK_FENCE, Item.Settings().group(ItemGroup.DECORATIONS))

    fun registerItems() {
        register(CHAIN_LINK, "chain_link")
        register(DYNAMITE, "dynamite")
        register(WRENCH, "wrench")

        registerBlockItem(GOLD_BUTTON, "gold_button")
        registerBlockItem(VARIABLE_REDSTONE_EMITTER, "variable_redstone_emitter")
        registerBlockItem(DIRT_GOLD_ORE, "dirt_gold_ore", false)
        registerBlockItem(SAND_GOLD_ORE, "sand_gold_ore", false)
        registerBlockItem(GRAVEL_GOLD_ORE, "gravel_gold_ore", false)
        registerBlockItem(CHAIN_LINK_FENCE, "chain_link_fence")
    }

    private fun register(item : Item, id : String) {
        if (Config.getItemOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }

    private fun registerBlockItem(item: BlockItem, id: String, respectsConfig : Boolean = true) {
        if (!respectsConfig || Config.getBlockOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }
}