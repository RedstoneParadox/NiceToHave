package redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.item.Items
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.config.ConfigOption

abstract class ItemsInitializer {

    protected fun register(item : Item?, id : String, respectsConfig: Boolean = true): Item? {
        if (!respectsConfig || Config.getBool("items.$id")) {
            return Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun register(item: Item?, id: String, configOption: String? = null): Item? {
        if (Config.getBool(configOption ?: "items.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun registerBlockItem(item: BlockItem?, id: String, respectsConfig : Boolean = true): BlockItem? {
        if (!respectsConfig || Config.getBool("blocks.$id")) {
            return Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
        return null
    }

    protected fun registerBlockItem(item: BlockItem?, id: String, configOption: String? = null): BlockItem? {
        if (Config.getBool(configOption ?: "blocks.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    protected fun registerPoleItem(pole : PoleBlock?, prefix : String): BlockItem? {
        if (pole != null) {
            return registerBlockItem(pole.createBlockItem(), "${prefix}_pole", "blocks.pole")
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