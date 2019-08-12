package redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import redstoneparadox.nicetohave.item.Items

object Fuels {

    fun registerFuels() {
        registerForEach(arrayOf(Items.OAK_POLE, Items.SPRUCE_POLE, Items.BIRCH_POLE, Items.JUNGLE_POLE, Items.ACACIA_POLE, Items.DARK_OAK_POLE), 300)
    }

    private fun registerForEach(fuels : Array<Item>, time : Int) {
        for (fuel in fuels) {
            register(fuel, time)
        }
    }

    private fun register(fuel : Item, time : Int) {
        FuelRegistry.INSTANCE.add(fuel, time)
    }
}