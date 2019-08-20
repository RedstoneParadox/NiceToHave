package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.util.config.Config

object Blocks {

    var GOLD_BUTTON : Block? = null
    var ANALOG_REDSTONE_EMITTER : Block? = null
    var CHAIN_LINK_FENCE : Block? = null
    var TRIMMED_VINE : Block? = null

    //Ore Blocks
    var DIRT_GOLD_ORE : Block? = null
    var SAND_GOLD_ORE : Block? = null
    var GRAVEL_GOLD_ORE : Block? = null

    //Poles
    var OAK_POLE : Block? = null
    var SPRUCE_POLE : Block? = null
    var BIRCH_POLE : Block? = null
    var JUNGLE_POLE : Block? = null
    var ACACIA_POLE : Block? = null
    var DARK_OAK_POLE : Block? = null

    fun registerBlocks() {
        GOLD_BUTTON = register(CustomButtonBlock(1), "gold_button")
        ANALOG_REDSTONE_EMITTER = register(AnalogRedstoneEmitterBlock(), "analog_redstone_emitter")
        CHAIN_LINK_FENCE = register(ChainLinkFenceBlock(), "chain_link_fence")
        TRIMMED_VINE = register(TrimmedVineBlock(), "trimmed_vine")

        DIRT_GOLD_ORE = register(Block(copySettings(Blocks.DIRT)), "dirt_gold_ore", false)
        SAND_GOLD_ORE = register(SandBlock(14406560, copySettings(Blocks.SAND)), "sand_gold_ore", false)
        GRAVEL_GOLD_ORE = register(GravelBlock(copySettings(Blocks.GRAVEL)), "gravel_gold_ore", false)

        if (Config.getBool("blocks.poles")) {
            OAK_POLE = register(PoleBlock(copySettings(Blocks.OAK_WOOD)), "oak_pole", false)
            SPRUCE_POLE =register(PoleBlock(copySettings(Blocks.SPRUCE_WOOD)), "spruce_pole", false)
            BIRCH_POLE = register(PoleBlock(copySettings(Blocks.BIRCH_WOOD)), "birch_pole", false)
            JUNGLE_POLE = register(PoleBlock(copySettings(Blocks.JUNGLE_WOOD)), "jungle_pole", false)
            ACACIA_POLE = register(PoleBlock(copySettings(Blocks.ACACIA_WOOD)), "acacia_pole", false)
            DARK_OAK_POLE = register(PoleBlock(copySettings(Blocks.DARK_OAK_WOOD)), "dark_oak_pole", false)

        }

        registerFlammables()
    }

    fun register(block : Block, id : String, respectConfig : Boolean = true): Block? {
        if (!respectConfig || Config.getBool("blocks.$id")) {
            Registry.register(Registry.BLOCK, "nicetohave:${id}", block)
        }
        return null
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

    private fun copySettings(block: Block): Block.Settings {
        return FabricBlockSettings.copy(block).build()
    }
}