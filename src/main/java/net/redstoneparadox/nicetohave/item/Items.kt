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

    fun registerItems() {
        register(CHAIN_LINK, "chain_link")
        register(DYNAMITE, "dynamite")
        register(WRENCH, "wrench")

        registerBlockItem(GOLD_BUTTON, "gold_button")
        registerBlockItem(VARIABLE_REDSTONE_EMITTER, "variable_redstone_emitter")
    }

    private fun register(item : Item, id : String) {
        if (Config.getItemOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }

    private fun registerBlockItem(item: BlockItem, id: String) {
        if (Config.getBlockOption(id, Config.boolType, true)) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }
}