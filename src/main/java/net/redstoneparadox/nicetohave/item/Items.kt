package net.redstoneparadox.nicetohave.item

import jdk.nashorn.internal.ir.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.item.wrench.WrenchItem

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items {

    val CHAIN_LINK : Item = Item(Item.Settings().group(ItemGroup.MATERIALS))
    val DYNAMITE : Item = DynamiteItem(Item.Settings().group(ItemGroup.TOOLS))
    val WRENCH : Item = WrenchItem(Item.Settings().group(ItemGroup.TOOLS))

    //BlockItems.
    val GOLD_BUTTON : BlockItem = BlockItem(Blocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))

    fun registerItems() {
        register(CHAIN_LINK, "chain_link")
        register(DYNAMITE, "dynamite")
        register(WRENCH, "wrench")

        register(GOLD_BUTTON, "gold_button")
    }

    private fun register(item : Item, id : String) {
        Registry.register(Registry.ITEM, "nicetohave:${id}", item)
    }

}