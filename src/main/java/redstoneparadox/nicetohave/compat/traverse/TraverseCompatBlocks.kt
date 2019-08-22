package redstoneparadox.nicetohave.compat.traverse

import com.terraformersmc.traverse.block.TraverseBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object TraverseCompatBlocks : BlocksInitializer() {

    val FIR_POLE : PoleBlock? = registerPole(TraverseBlocks.FIR_WOOD, "fir", "traverse")

    fun initBlocks() {
        if (Config.getBool("blocks.poles")) {
            registerFlammableBlock(FIR_POLE, FlammableBlockRegistry.Entry(5, 20))
        }
    }
}