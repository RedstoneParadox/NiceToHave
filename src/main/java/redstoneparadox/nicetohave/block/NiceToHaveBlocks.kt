package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.GravelBlock
import net.minecraft.block.SandBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.config.Config

object NiceToHaveBlocks : BlocksInitializer() {

    val GOLD_BUTTON : Block? = register("gold_button", CustomButtonBlock(1), Config.Redstone.goldButton)
    val ANALOG_REDSTONE_EMITTER : Block? = register("analog_redstone_emitter", AnalogRedstoneEmitterBlock(), Config.Redstone.analogRedstoneEmitter)
    val CHAIN_LINK_FENCE : ChainLinkFenceBlock? = register("chain_link_fence", ChainLinkFenceBlock(), false)
    val TRIMMED_VINE : Block? = register("trimmed_vine", TrimmedVineBlock(), Config.Blocks.trimmedVines)
    val FUSED_WARPED_WART: Block? = register("fused_warped_wart", FusedWarpedWartBlock(), false)

    //Ore Blocks
    val DIRT_GOLD_ORE : Block = register("dirt_gold_ore", Block(copySettings(Blocks.DIRT)))
    val SAND_GOLD_ORE : Block = register("sand_gold_ore", SandBlock(14406560, copySettings(Blocks.SAND)))
    val GRAVEL_GOLD_ORE : Block = register("gravel_gold_ore", GravelBlock(copySettings(Blocks.GRAVEL)))

    //Poles
    val OAK_POLE: PoleBlock? = registerWoodPole("oak", Blocks.OAK_WOOD)
    val SPRUCE_POLE: PoleBlock? = registerWoodPole("spruce", Blocks.SPRUCE_WOOD)
    val BIRCH_POLE: PoleBlock? = registerWoodPole("birch", Blocks.BIRCH_WOOD)
    val JUNGLE_POLE: PoleBlock? = registerWoodPole("jungle", Blocks.JUNGLE_WOOD)
    val ACACIA_POLE: PoleBlock? = registerWoodPole("acacia", Blocks.ACACIA_WOOD)
    val DARK_OAK_POLE: PoleBlock? = registerWoodPole("dark_oak", Blocks.DARK_OAK_WOOD)
    val WARPED_POLE: PoleBlock? = registerWoodPole("warped", Blocks.WARPED_STEM)
    val CRIMSON_POLE: PoleBlock? = registerWoodPole("crimson", Blocks.CRIMSON_STEM)

    val STRIPPED_OAK_POLE: PoleBlock? = registerWoodPole("stripped_oak", Blocks.OAK_WOOD)
    val STRIPPED_SPRUCE_POLE: PoleBlock? = registerWoodPole("stripped_spruce", Blocks.SPRUCE_WOOD)
    val STRIPPED_BIRCH_POLE: PoleBlock? = registerWoodPole("stripped_birch", Blocks.BIRCH_WOOD)
    val STRIPPED_JUNGLE_POLE: PoleBlock? = registerWoodPole("stripped_jungle", Blocks.JUNGLE_WOOD)
    val STRIPPED_ACACIA_POLE: PoleBlock? = registerWoodPole("stripped_acacia", Blocks.ACACIA_WOOD)
    val STRIPPED_DARK_OAK_POLE: PoleBlock? = registerWoodPole("stripped_dark_oak", Blocks.DARK_OAK_WOOD)
    val STRIPPED_WARPED_POLE: PoleBlock? = registerWoodPole("stripped_warped", Blocks.WARPED_STEM)
    val STRIPPED_CRIMSON_POLE: PoleBlock? = registerWoodPole("stripped_crimson", Blocks.CRIMSON_STEM)

    fun initBlocks() {
        if (Config.Blocks.poles) {
            registerFlammableBlocks(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }

    fun mapPolesToStrippedPoles(): Map<Block?, Block?> {
        return mapOf<Block?, Block?>(
                OAK_POLE to STRIPPED_OAK_POLE,
                SPRUCE_POLE to STRIPPED_SPRUCE_POLE,
                BIRCH_POLE to STRIPPED_BIRCH_POLE,
                JUNGLE_POLE to STRIPPED_JUNGLE_POLE,
                ACACIA_POLE to STRIPPED_ACACIA_POLE,
                DARK_OAK_POLE to STRIPPED_DARK_OAK_POLE,
                WARPED_POLE to STRIPPED_WARPED_POLE,
                CRIMSON_POLE to STRIPPED_CRIMSON_POLE
        )
    }
}