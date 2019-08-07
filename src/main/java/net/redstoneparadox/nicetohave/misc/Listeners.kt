package net.redstoneparadox.nicetohave.misc

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.DispenserBlock
import net.minecraft.block.SaplingBlock
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import net.minecraft.world.loot.UniformLootTableRange
import net.minecraft.world.loot.entry.ItemEntry
import net.minecraft.world.loot.function.SetCountLootFunction
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.util.Config
import net.minecraft.item.Items as VanillaItems

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
object Listeners {

    init {
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { resourceManager, manager, id, supplier, setter ->
            if ("minecraft:chests/abandoned_mineshaft" == id.toString() && Config.getItemOption("dynamite", Config.boolType, true)) {
                val poolBuider = FabricLootPoolBuilder.builder()
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.0f, 5.0f)))
                        .withEntry(ItemEntry.builder(Items.DYNAMITE).setWeight(8))

                supplier.withPool(poolBuider)
            }
        })
        RegistryEntryAddedCallback.event(Registry.ITEM).register(RegistryEntryAddedCallback<Item> { rawId, id, item ->
            val sapling : Block = Registry.BLOCK.get(id)
            if (sapling is SaplingBlock && Config.getMiscOption("dispenser_crop_planting", Config.boolType, true)) {
                val saplingFarmBlocks: Array<Block> = if (farmlandPlantable(item)) arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND) else arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK)
                DispenserBlock.registerBehavior(item, DispenserBehaviors.PlantingDispenserBehavior(saplingFarmBlocks, sapling))
            }
        })
    }

    private fun farmlandPlantable(saplingItem : Item): Boolean {
        return (saplingItem == VanillaItems.OAK_SAPLING) || (saplingItem == VanillaItems.BIRCH_SAPLING) || (saplingItem == VanillaItems.SPRUCE_SAPLING) || (saplingItem == VanillaItems.JUNGLE_SAPLING)
    }
}