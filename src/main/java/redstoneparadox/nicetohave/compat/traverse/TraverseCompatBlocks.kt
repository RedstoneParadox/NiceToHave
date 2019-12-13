package redstoneparadox.nicetohave.compat.traverse

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.util.newconfig.Config

object TraverseCompatBlocks : BlocksInitializer() {

    val FIR_POLE : PoleBlock? = registerWoodPole("fir", Blocks.OAK_WOOD)
    val STRIPPED_FIR_POLE : PoleBlock? = registerWoodPole("stripped_fir", Blocks.STRIPPED_OAK_WOOD)

    fun initBlocks() {
        if (Config.Blocks.poles) {
            registerFlammableBlocks(arrayOf(FIR_POLE, STRIPPED_FIR_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }

    fun mapPolesToStrippedPoles(): Map<Block?, Block?> {
        return mapOf<Block?, Block?>(
                FIR_POLE to STRIPPED_FIR_POLE
        )
    }
}