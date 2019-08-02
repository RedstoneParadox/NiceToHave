package net.redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.world.loot.UniformLootTableRange
import net.minecraft.world.loot.entry.ItemEntry
import net.minecraft.world.loot.function.SetCountLootFunction
import net.redstoneparadox.nicetohave.item.Items

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
object Listeners {

    fun registerListeners() {
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { resourceManager, manager, id, supplier, setter ->
            if ("minecraft:chests/abandoned_mineshaft" == id.toString()) {
                val poolBuider = FabricLootPoolBuilder.builder()
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.0f, 5.0f)))
                        .withEntry(ItemEntry.builder(Items.DYNAMITE).setWeight(8))

                supplier.withPool(poolBuider)
            }
        })
    }
}