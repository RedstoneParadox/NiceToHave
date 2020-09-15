package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import io.github.redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import io.github.redstoneparadox.nicetohave.config.Config

object NiceToHaveBlocks : BlocksInitializer() {

    val GOLD_BUTTON : Block = register("gold_button", CustomButtonBlock(1))
    val ANALOG_REDSTONE_EMITTER : Block = register("analog_redstone_emitter", AnalogRedstoneEmitterBlock())
    val CHAIN_LINK_FENCE : ChainLinkFenceBlock? = register("chain_link_fence", ChainLinkFenceBlock(), false)
    val TRIMMED_VINE : Block = register("trimmed_vine", TrimmedVineBlock())

    //Poles
    val OAK_POLE: PoleBlock = registerPole("oak", Blocks.OAK_WOOD)
    val SPRUCE_POLE: PoleBlock = registerPole("spruce", Blocks.SPRUCE_WOOD)
    val BIRCH_POLE: PoleBlock = registerPole("birch", Blocks.BIRCH_WOOD)
    val JUNGLE_POLE: PoleBlock = registerPole("jungle", Blocks.JUNGLE_WOOD)
    val ACACIA_POLE: PoleBlock = registerPole("acacia", Blocks.ACACIA_WOOD)
    val DARK_OAK_POLE: PoleBlock = registerPole("dark_oak", Blocks.DARK_OAK_WOOD)
    val WARPED_POLE: PoleBlock = registerPole("warped", Blocks.WARPED_STEM)
    val CRIMSON_POLE: PoleBlock = registerPole("crimson", Blocks.CRIMSON_STEM)

    val STRIPPED_OAK_POLE: PoleBlock = registerPole("stripped_oak", Blocks.OAK_WOOD)
    val STRIPPED_SPRUCE_POLE: PoleBlock = registerPole("stripped_spruce", Blocks.SPRUCE_WOOD)
    val STRIPPED_BIRCH_POLE: PoleBlock = registerPole("stripped_birch", Blocks.BIRCH_WOOD)
    val STRIPPED_JUNGLE_POLE: PoleBlock = registerPole("stripped_jungle", Blocks.JUNGLE_WOOD)
    val STRIPPED_ACACIA_POLE: PoleBlock = registerPole("stripped_acacia", Blocks.ACACIA_WOOD)
    val STRIPPED_DARK_OAK_POLE: PoleBlock = registerPole("stripped_dark_oak", Blocks.DARK_OAK_WOOD)
    val STRIPPED_WARPED_POLE: PoleBlock = registerPole("stripped_warped", Blocks.WARPED_STEM)
    val STRIPPED_CRIMSON_POLE: PoleBlock = registerPole("stripped_crimson", Blocks.CRIMSON_STEM)

    fun initBlocks() {
        registerFlammableBlocks(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
        registerFlammableBlocks(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
    }

    fun mapPolesToStrippedPoles(): Map<Block, Block> {
        return mapOf<Block, Block>(
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