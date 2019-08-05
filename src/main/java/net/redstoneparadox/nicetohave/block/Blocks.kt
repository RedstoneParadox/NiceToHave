package net.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.Material
import net.minecraft.util.registry.Registry

object Blocks {

    val GOLD_BUTTON : Block = CustomButtonBlock(1, FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByHand(true).build())
    //val VARIABLE_REDSTONE_EMITTER = VariableRedstoneBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build())

    fun registerBlocks() {
        register(GOLD_BUTTON, "gold_button")
        //register(VARIABLE_REDSTONE_EMITTER, "variable_redstone_emitter")
    }

    fun register(block : Block, id : String) {
        Registry.register(Registry.BLOCK, "nicetohave:${id}", block)
    }
}