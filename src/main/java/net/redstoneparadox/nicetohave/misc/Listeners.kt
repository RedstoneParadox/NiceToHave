package net.redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.block.*
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import net.minecraft.world.loot.UniformLootTableRange
import net.minecraft.world.loot.entry.ItemEntry
import net.minecraft.world.loot.function.SetCountLootFunction
import net.redstoneparadox.nicetohave.hooks.SeedGetter
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.item.wrench.WrenchItem
import net.redstoneparadox.nicetohave.util.Config
import net.minecraft.item.Items as VanillaItems

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
object Listeners {

    fun initListeners() {
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { resourceManager, manager, id, supplier, setter ->
            if ("minecraft:chests/abandoned_mineshaft" == id.toString() && Config.getItemOption("dynamite", Config.boolType, true)) {
                val poolBuider = FabricLootPoolBuilder.builder()
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.0f, 5.0f)))
                        .withEntry(ItemEntry.builder(Items.DYNAMITE).setWeight(8))

                supplier.withPool(poolBuider)
            }
        })
        RegistryEntryAddedCallback.event(Registry.ITEM).register(RegistryEntryAddedCallback<Item> { rawId, id, item ->
            val sapling = Registry.BLOCK.get(id)
            if (sapling is SaplingBlock && Config.getMiscOption("dispenser_crop_planting", Config.boolType, true)) {
                val saplingFarmBlocks: Array<Block> = DispenserBehaviors.saplingFarmBlocks
                DispenserBlock.registerBehavior(item, DispenserBehaviors.PlantingDispenserBehavior(saplingFarmBlocks, sapling))
            }
        })
        RegistryEntryAddedCallback.event(Registry.BLOCK).register(RegistryEntryAddedCallback {rawId, id, block ->
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, id)
        })
    }
}