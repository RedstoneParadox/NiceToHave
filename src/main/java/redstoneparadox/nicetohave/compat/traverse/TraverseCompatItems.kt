package redstoneparadox.nicetohave.compat.traverse

import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TraverseCompatItems : ItemsInitializer() {

    val FIR_POLE : BlockItem? = registerPoleItem(TraverseCompatBlocks.FIR_POLE, "fir")
    val STRIPPED_FIR_POLE : BlockItem? = registerPoleItem(TraverseCompatBlocks.STRIPPED_FIR_POLE, "stripped_fir")

    fun initItems() {
        if (Config.Blocks.poles) {
            registerFuelForEach(arrayOf(STRIPPED_FIR_POLE, FIR_POLE), 300)
        }
    }
}