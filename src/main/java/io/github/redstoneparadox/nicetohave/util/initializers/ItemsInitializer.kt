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

    @JvmName("registerBlockItem1")
    protected fun registerBlockItem(id: String, block: Block?, settings: Item.Settings): BlockItem? {
        if (block != null) {
            return register(id, BlockItem(block, settings))
        }
        return null
    }

    protected fun registerBlockItem(id: String, block: Block, settings: Item.Settings): BlockItem {
        return register(id, BlockItem(block, settings))
    }

    @JvmName("registerWoodPoleItem1")
    protected fun registerWoodPoleItem(prefix: String, pole: PoleBlock?): BlockItem? {
        return registerBlockItem("${prefix}_pole", pole, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    protected fun registerWoodPoleItem(prefix: String, pole: PoleBlock): BlockItem {
        return registerBlockItem("${prefix}_pole", pole, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    protected fun registerFuelForEach(fuels : Array<Item?>, time : Int) {
        for (fuel in fuels) {
            registerFuel(fuel, time)
        }
    }

    protected fun registerFuel(fuel : Item?, time : Int) {
        if (fuel != null) {
            FuelRegistry.INSTANCE.add(fuel, time)
        }
    }
}