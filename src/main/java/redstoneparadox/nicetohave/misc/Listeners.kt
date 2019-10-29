package redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.util.registry.Registry
import net.minecraft.world.loot.UniformLootTableRange
import net.minecraft.world.loot.entry.ItemEntry
import net.minecraft.world.loot.function.SetCountLootFunction
import redstoneparadox.nicetohave.item.NiceToHaveItems
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.util.config.Config

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
object Listeners {

    fun initListeners() {
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { resourceManager, manager, id, supplier, setter ->
            if (Config.Items.dynamite && "minecraft:chests/abandoned_mineshaft" == id.toString()) {
                val poolBuider = FabricLootPoolBuilder.builder()
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.0f, 5.0f)))
                        .withEntry(ItemEntry.builder(NiceToHaveItems.DYNAMITE).setWeight(8))

                supplier.withPool(poolBuider)
            }
        })
        RegistryEntryAddedCallback.event(Registry.BLOCK).register(RegistryEntryAddedCallback {rawId, id, block ->
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, id)
        })
    }
}