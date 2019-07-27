package net.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

object Blocks {

    val GOLD_BUTTON : Block = CustomButtonBlock(1, FabricBlockSettings.of(Material.METAL).build())

    fun registerBlocks() {
        register(GOLD_BUTTON, "gold_button")
    }

    fun register(block : Block, id : String) {
        Registry.register(Registry.BLOCK, "nicetohave:${id}", block)
    }
}