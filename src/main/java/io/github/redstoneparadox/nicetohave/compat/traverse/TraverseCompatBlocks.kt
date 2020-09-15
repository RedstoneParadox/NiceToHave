package io.github.redstoneparadox.nicetohave.compat.traverse

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import io.github.redstoneparadox.nicetohave.block.PoleBlock
import io.github.redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import io.github.redstoneparadox.nicetohave.config.Config

object TraverseCompatBlocks : BlocksInitializer() {

    val FIR_POLE : PoleBlock = registerPole("fir", Blocks.OAK_WOOD)
    val STRIPPED_FIR_POLE : PoleBlock = registerPole("stripped_fir", Blocks.STRIPPED_OAK_WOOD)

    fun initBlocks() {
        registerFlammableBlocks(arrayOf(FIR_POLE, STRIPPED_FIR_POLE), FlammableBlockRegistry.Entry(5, 20))
    }

    fun mapPolesToStrippedPoles(): Map<Block?, Block?> {
        return mapOf<Block?, Block?>(
                FIR_POLE to STRIPPED_FIR_POLE
        )
    }
}