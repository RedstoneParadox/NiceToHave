package redstoneparadox.nicetohave.compat.traverse

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Blocks
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object TraverseCompatBlocks : BlocksInitializer() {

    val FIR_POLE : PoleBlock? = registerWoodPole("fir", Blocks.OAK_WOOD, "traverse")
    val STRIPPED_FIR_POLE : PoleBlock? = registerWoodPole("stripped_fir", Blocks.STRIPPED_OAK_WOOD, "traverse")

    fun initBlocks() {
        if (OldConfig.Blocks.poles) {
            registerFlammableBlocks(arrayOf(FIR_POLE, STRIPPED_FIR_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}