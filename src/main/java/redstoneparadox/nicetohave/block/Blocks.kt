package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.nbt.Tag
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.util.Config

object Blocks {

    val GOLD_BUTTON : Block = CustomButtonBlock(1, FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByHand(true).build())
    val ANALOG_REDSTONE_EMITTER = AnalogRedstoneEmitterBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build())
    val CHAIN_LINK_FENCE : Block = ChainLinkFenceBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByTool(net.minecraft.tag.Tag(Identifier("fabric:pickaxes"))).build())
    val TRIMMED_VINE_BLOCK : Block = TrimmedVineBlock(FabricBlockSettings.copy(Blocks.VINE).build())

    //Ore Blocks
    val DIRT_GOLD_ORE : Block = Block(FabricBlockSettings.copy(Blocks.DIRT).build())
    val SAND_GOLD_ORE : Block = SandBlock(14406560, FabricBlockSettings.copy(Blocks.SAND).build())
    val GRAVEL_GOLD_ORE : Block = GravelBlock(FabricBlockSettings.copy(Blocks.GRAVEL).build())

    //Poles
    val OAK_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).build())
    val SPRUCE_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.SPRUCE_WOOD).build())
    val BIRCH_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.BIRCH_WOOD).build())
    val JUNGLE_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.JUNGLE_WOOD).build())
    val ACACIA_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.ACACIA_WOOD).build())
    val DARK_OAK_POLE : Block = PoleBlock(FabricBlockSettings.copy(Blocks.DARK_OAK_WOOD).build())

    fun registerBlocks() {
        register(GOLD_BUTTON, "gold_button")
        register(ANALOG_REDSTONE_EMITTER, "analog_redstone_emitter")
        register(CHAIN_LINK_FENCE, "chain_link_fence")
        register(TRIMMED_VINE_BLOCK, "trimmed_vine")

        register(DIRT_GOLD_ORE, "dirt_gold_ore", false)
        register(SAND_GOLD_ORE, "sand_gold_ore", false)
        register(GRAVEL_GOLD_ORE, "gravel_gold_ore", false)

        if (Config.getBlockOption("poles", Config.boolType, true)) {
            register(OAK_POLE, "oak_pole", false)
            register(SPRUCE_POLE, "spruce_pole", false)
            register(BIRCH_POLE, "birch_pole", false)
            register(JUNGLE_POLE, "jungle_pole", false)
            register(ACACIA_POLE, "acacia_pole", false)
            register(DARK_OAK_POLE, "dark_oak_pole", false)
        }

        registerFlammables()
    }

    fun register(block : Block, id : String, respectConfig : Boolean = true) {
        if (!respectConfig || Config.getBlockOption(id, Config.boolType, true)) {
            Registry.register(Registry.BLOCK, "nicetohave:${id}", block)
        }
    }

    private fun registerFlammables() {
        registerFlammableBlocks(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
    }

    private fun registerFlammableBlocks(blocks : Array<Block>, entry : FlammableBlockRegistry.Entry) {
        for (block in blocks) {
            registerFlammableBlock(block, entry)
        }
    }

    private fun registerFlammableBlock(block: Block, entry : FlammableBlockRegistry.Entry) {
        FlammableBlockRegistry.getDefaultInstance().add(block, entry)
    }
}