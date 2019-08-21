package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object Blocks : BlocksInitializer() {

    val GOLD_BUTTON : Block? = register(CustomButtonBlock(1), "gold_button", null)
    val ANALOG_REDSTONE_EMITTER : Block? = register(AnalogRedstoneEmitterBlock(), "analog_redstone_emitter", null)
    val CHAIN_LINK_FENCE : Block? = register(ChainLinkFenceBlock(), "chain_link_fence", null)
    val TRIMMED_VINE : Block? = register(TrimmedVineBlock(), "trimmed_vine", null)

    //Ore Blocks
    val DIRT_GOLD_ORE : Block? = register(Block(copySettings(Blocks.DIRT)), "dirt_gold_ore", false)
    val SAND_GOLD_ORE : Block? = register(SandBlock(14406560, copySettings(Blocks.SAND)), "sand_gold_ore", false)
    val GRAVEL_GOLD_ORE : Block? = register(GravelBlock(copySettings(Blocks.GRAVEL)), "gravel_gold_ore", false)

    //Poles
    val OAK_POLE : Block? = register(PoleBlock(copySettings(Blocks.OAK_WOOD)), "oak_pole", "blocks.poles")
    val SPRUCE_POLE : Block? = register(PoleBlock(copySettings(Blocks.SPRUCE_WOOD)), "spruce_pole", "blocks.poles")
    val BIRCH_POLE : Block? = register(PoleBlock(copySettings(Blocks.BIRCH_WOOD)), "birch_pole", "blocks.poles")
    val JUNGLE_POLE : Block? = register(PoleBlock(copySettings(Blocks.JUNGLE_WOOD)), "jungle_pole", "blocks.poles")
    val ACACIA_POLE : Block? = register(PoleBlock(copySettings(Blocks.ACACIA_WOOD)), "acacia_pole", "blocks.poles")
    val DARK_OAK_POLE : Block? = register(PoleBlock(copySettings(Blocks.DARK_OAK_WOOD)), "dark_oak_pole", "blocks.poles")

    fun init() {
        if (Config.getBool("blocks.poles")) {
            registerFlammableBlocks(arrayOf(OAK_POLE!!, SPRUCE_POLE!!, BIRCH_POLE!!, JUNGLE_POLE!!, ACACIA_POLE!!, DARK_OAK_POLE!!), FlammableBlockRegistry.Entry(5, 20))
        }
    }

}