package io.github.redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.item.NiceToHaveItems
import io.github.redstoneparadox.nicetohave.item.wrench.WrenchItem
import io.github.redstoneparadox.nicetohave.config.Config
import net.minecraft.loot.provider.number.UniformLootNumberProvider

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
object Listeners {

    fun initListeners() {
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { resourceManager, manager, id, supplier, setter ->
            if (Config.Items.dynamite && "minecraft:chests/abandoned_mineshaft" == id.toString()) {
                val poolBuider = FabricLootPoolBuilder.builder()
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 5.0f)).build())
                        .withEntry(ItemEntry.builder(NiceToHaveItems.DYNAMITE).weight(8).build())

                supplier.withPool(poolBuider.build())
            }
        })
        RegistryEntryAddedCallback.event(Registry.BLOCK).register(RegistryEntryAddedCallback {rawId, id, block ->
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, id)
        })
    }
}