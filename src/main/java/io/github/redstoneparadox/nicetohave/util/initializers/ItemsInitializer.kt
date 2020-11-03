package io.github.redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.block.PoleBlock

abstract class ItemsInitializer {
    protected fun <T : Item> register(id: String, item: T): T {
        return Registry.register(Registry.ITEM, "nicetohave:$id", item)
    }

    protected fun <T : Item> register(id: String, item: T, vararg conditions : Boolean): T? {
        for (condition in conditions) {
            if (!condition) return null
        }
        return register(id, item)
    }

    protected fun poleItem(pole: PoleBlock): BlockItem {
        return BlockItem(pole, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    protected fun registerFuelForEach(fuels : Array<Item?>, time : Int) {
        for (fuel in fuels) {
            registerFuel(fuel, time)
        }
    }

    private fun registerFuel(fuel : Item?, time : Int) {
        if (fuel != null) {
            FuelRegistry.INSTANCE.add(fuel, time)
        }
    }
}