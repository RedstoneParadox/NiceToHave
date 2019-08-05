package net.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.util.Config

object Blocks {

    val GOLD_BUTTON : Block = CustomButtonBlock(1, FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByHand(true).build())
    val VARIABLE_REDSTONE_EMITTER = VariableRedstoneBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build())
    val CHAIN_LINK_FENCE : Block = ChainLinkFenceBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByTool(Tag(Identifier("fabric:pickaxes"))).build())
    val TRIMMED_VINE_BLOCK : Block = TrimmedVineBlock(FabricBlockSettings.copy(Blocks.VINE).build())

    //Ore Blocks
    val DIRT_GOLD_ORE : Block = Block(FabricBlockSettings.copy(Blocks.DIRT).build())
    val SAND_GOLD_ORE : Block = SandBlock(14406560, FabricBlockSettings.copy(Blocks.SAND).build())
    val GRAVEL_GOLD_ORE : Block = GravelBlock(FabricBlockSettings.copy(Blocks.GRAVEL).build())

    fun registerBlocks() {
        register(GOLD_BUTTON, "gold_button")
        register(VARIABLE_REDSTONE_EMITTER, "variable_redstone_emitter")
        register(CHAIN_LINK_FENCE, "chain_link_fence")
        register(TRIMMED_VINE_BLOCK, "trimmed_vine")

        register(DIRT_GOLD_ORE, "dirt_gold_ore", false)
        register(SAND_GOLD_ORE, "sand_gold_ore", false)
        register(GRAVEL_GOLD_ORE, "gravel_gold_ore", false)
    }

    fun register(block : Block, id : String, respectConfig : Boolean = true) {
        if (!respectConfig || Config.getBlockOption(id, Config.boolType, true)) {
            Registry.register(Registry.BLOCK, "nicetohave:${id}", block)
        }
    }
}