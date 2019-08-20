package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.util.BlocksInitializer
import redstoneparadox.nicetohave.util.config.Config

object Blocks : BlocksInitializer() {

    val GOLD_BUTTON : Block?
    val ANALOG_REDSTONE_EMITTER : Block?
    val CHAIN_LINK_FENCE : Block?
    val TRIMMED_VINE : Block?

    //Ore Blocks
    val DIRT_GOLD_ORE : Block?
    val SAND_GOLD_ORE : Block?
    val GRAVEL_GOLD_ORE : Block?

    //Poles
    val OAK_POLE : Block?
    val SPRUCE_POLE : Block?
    val BIRCH_POLE : Block?
    val JUNGLE_POLE : Block?
    val ACACIA_POLE : Block?
    val DARK_OAK_POLE : Block?

    fun init() {

    }

    init {
        GOLD_BUTTON = register(CustomButtonBlock(1), "gold_button", null)
        ANALOG_REDSTONE_EMITTER = register(AnalogRedstoneEmitterBlock(), "analog_redstone_emitter", null)
        CHAIN_LINK_FENCE = register(ChainLinkFenceBlock(), "chain_link_fence", null)
        TRIMMED_VINE = register(TrimmedVineBlock(), "trimmed_vine", null)

        DIRT_GOLD_ORE = register(Block(copySettings(Blocks.DIRT)), "dirt_gold_ore", false)
        SAND_GOLD_ORE = register(SandBlock(14406560, copySettings(Blocks.SAND)), "sand_gold_ore", false)
        GRAVEL_GOLD_ORE = register(GravelBlock(copySettings(Blocks.GRAVEL)), "gravel_gold_ore", false)

        OAK_POLE = register(PoleBlock(copySettings(Blocks.OAK_WOOD)), "oak_pole", "blocks.poles")
        SPRUCE_POLE = register(PoleBlock(copySettings(Blocks.SPRUCE_WOOD)), "spruce_pole", "blocks.poles")
        BIRCH_POLE = register(PoleBlock(copySettings(Blocks.BIRCH_WOOD)), "birch_pole", "blocks.poles")
        JUNGLE_POLE = register(PoleBlock(copySettings(Blocks.JUNGLE_WOOD)), "jungle_pole", "blocks.poles")
        ACACIA_POLE = register(PoleBlock(copySettings(Blocks.ACACIA_WOOD)), "acacia_pole", "blocks.poles")
        DARK_OAK_POLE = register(PoleBlock(copySettings(Blocks.DARK_OAK_WOOD)), "dark_oak_pole", "blocks.poles")

        registerFlammables()
    }

    private fun registerFlammables() {
        if (Config.getBool("blocks.poles")) {
            registerFlammableBlocks(arrayOf(OAK_POLE!!, SPRUCE_POLE!!, BIRCH_POLE!!, JUNGLE_POLE!!, ACACIA_POLE!!, DARK_OAK_POLE!!), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}