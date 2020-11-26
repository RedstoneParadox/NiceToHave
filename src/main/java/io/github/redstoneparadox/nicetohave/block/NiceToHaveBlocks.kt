package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import io.github.redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import io.github.redstoneparadox.nicetohave.config.Config

object NiceToHaveBlocks : BlocksInitializer() {
    val GOLD_BUTTON : Block = CustomButtonBlock(1)
    val ANALOG_REDSTONE_EMITTER : Block = AnalogRedstoneEmitterBlock()
    val TRIMMED_VINE : Block = TrimmedVineBlock()

    //Poles
    val OAK_POLE: PoleBlock = PoleBlock(Blocks.OAK_WOOD)
    val SPRUCE_POLE: PoleBlock = PoleBlock(Blocks.SPRUCE_WOOD)
    val BIRCH_POLE: PoleBlock = PoleBlock(Blocks.BIRCH_WOOD)
    val JUNGLE_POLE: PoleBlock = PoleBlock(Blocks.JUNGLE_WOOD)
    val ACACIA_POLE: PoleBlock = PoleBlock(Blocks.ACACIA_WOOD)
    val DARK_OAK_POLE: PoleBlock = PoleBlock(Blocks.DARK_OAK_WOOD)
    val WARPED_POLE: PoleBlock = PoleBlock(Blocks.WARPED_STEM)
    val CRIMSON_POLE: PoleBlock = PoleBlock(Blocks.CRIMSON_STEM)

    val STRIPPED_OAK_POLE: PoleBlock = PoleBlock(Blocks.OAK_WOOD)
    val STRIPPED_SPRUCE_POLE: PoleBlock = PoleBlock(Blocks.SPRUCE_WOOD)
    val STRIPPED_BIRCH_POLE: PoleBlock = PoleBlock(Blocks.BIRCH_WOOD)
    val STRIPPED_JUNGLE_POLE: PoleBlock = PoleBlock(Blocks.JUNGLE_WOOD)
    val STRIPPED_ACACIA_POLE: PoleBlock = PoleBlock(Blocks.ACACIA_WOOD)
    val STRIPPED_DARK_OAK_POLE: PoleBlock = PoleBlock(Blocks.DARK_OAK_WOOD)
    val STRIPPED_WARPED_POLE: PoleBlock = PoleBlock(Blocks.WARPED_STEM)
    val STRIPPED_CRIMSON_POLE: PoleBlock = PoleBlock(Blocks.CRIMSON_STEM)

    fun initBlocks() {
        register("gold_button", GOLD_BUTTON)
        register("analog_redstone_emitter", ANALOG_REDSTONE_EMITTER)
        register("trimmed_vine", TRIMMED_VINE)

        register("oak_pole", OAK_POLE)
        register("spruce_pole", SPRUCE_POLE)
        register("birch_pole", BIRCH_POLE)
        register("jungle_pole", JUNGLE_POLE)
        register("acacia_pole", ACACIA_POLE)
        register("dark_oak_pole", DARK_OAK_POLE)
        register("warped_pole", WARPED_POLE)
        register("crimson_pole", CRIMSON_POLE)

        register("stripped_oak_pole", STRIPPED_OAK_POLE)
        register("stripped_spruce_pole", STRIPPED_SPRUCE_POLE)
        register("stripped_birch_pole", STRIPPED_BIRCH_POLE)
        register("stripped_jungle_pole", STRIPPED_JUNGLE_POLE)
        register("stripped_acacia_pole", STRIPPED_ACACIA_POLE)
        register("stripped_dark_oak_pole", STRIPPED_DARK_OAK_POLE)
        register("stripped_warped_pole", STRIPPED_WARPED_POLE)
        register("stripped_crimson_pole", STRIPPED_CRIMSON_POLE)

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