package redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.oldconfig.OldConfig

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

    protected fun registerBlockItem(id: String, block: Block?, settings: Item.Settings): BlockItem? {
        if (block != null) {
            return register(id, BlockItem(block, settings))
        }
        return null
    }

    protected fun registerWoodPoleItem(prefix: String, pole: PoleBlock?): BlockItem? {
        return registerBlockItem("${prefix}_pole", pole, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    protected fun register(item : Item?, id : String, respectsConfig: Boolean = true): Item? {
        if (!respectsConfig || OldConfig.getBool("items.$id")) {
            return Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun register(item: Item?, id: String, configOption: String? = null): Item? {
        if (OldConfig.getBool(configOption ?: "items.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun registerBlockItem(item: BlockItem?, id: String, respectsConfig : Boolean = true): BlockItem? {
        if (!respectsConfig || OldConfig.getBool("blocks.$id")) {
            return Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
        return null
    }

    protected fun registerBlockItem(item: BlockItem?, id: String, configOption: String? = null): BlockItem? {
        if (OldConfig.getBool(configOption ?: "blocks.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun registerPoleItem(pole : PoleBlock?, prefix : String): BlockItem? {
        if (pole != null) {
            return registerBlockItem(pole.createBlockItem(), "${prefix}_pole", "blocks.poles")
        }
        return null
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