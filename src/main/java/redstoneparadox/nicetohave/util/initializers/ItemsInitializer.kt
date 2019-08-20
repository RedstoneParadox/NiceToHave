package redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.item.Items
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.config.ConfigOption

abstract class ItemsInitializer {

    protected fun register(item : Item, id : String, respectsConfig: Boolean = true) {
        if (!respectsConfig || Config.getBool("items.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
    }

    protected fun register(item: Item, id: String, configOption: String? = null): Item? {
        if (Config.getBool(configOption ?: "items.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    private fun registerBlockItem(item: BlockItem, id: String, respectsConfig : Boolean = true) {
        if (!respectsConfig || Config.getBool("blocks.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:${id}", item)
        }
    }

    protected fun registerBlockItem(item: Item, id: String, configOption: String? = null): Item? {
        if (Config.getBool(configOption ?: "blocks.$id")) {
            Registry.register(Registry.ITEM, "nicetohave:$id", item)
        }
        return null
    }

    private fun registerFuelForEach(fuels : Array<Item?>, time : Int) {
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