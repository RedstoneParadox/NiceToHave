package net.redstoneparadox.nicetohave.item

import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items {

    val CHAIN_LINK : Item = Item(Item.Settings().itemGroup(ItemGroup.MATERIALS))
    val DYNAMITE : Item = DynamiteItem(Item.Settings().itemGroup(ItemGroup.TOOLS))

    fun registerItems() {
        register(CHAIN_LINK, "chain_link")
        register(DYNAMITE, "dynamite")

        DynamiteItem.registerDispenserBehavior()
    }

    fun register(item : Item, id : String) {
        Registry.register(Registry.ITEM, "nicetohave:${id}", item)
    }
}