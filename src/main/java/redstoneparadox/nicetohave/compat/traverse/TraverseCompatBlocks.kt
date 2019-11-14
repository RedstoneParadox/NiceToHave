package redstoneparadox.nicetohave.compat.traverse

import com.terraformersmc.traverse.block.TraverseBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object TraverseCompatBlocks : BlocksInitializer() {

    val FIR_POLE : PoleBlock? = registerWoodPole("fir", TraverseBlocks.FIR_WOOD, "traverse")
    val STRIPPED_FIR_POLE : PoleBlock? = registerWoodPole("stripped_fir", TraverseBlocks.FIR_WOOD, "traverse")

    fun initBlocks() {
        if (OldConfig.Blocks.poles) {
            registerFlammableBlocks(arrayOf(FIR_POLE, STRIPPED_FIR_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}