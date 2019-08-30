package redstoneparadox.nicetohave.compat.traverse

import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TraverseCompatItems : ItemsInitializer() {

    val FIR_POLE : BlockItem? = registerWoodPoleItem("fir", TraverseCompatBlocks.FIR_POLE)
    val STRIPPED_FIR_POLE : BlockItem? = registerWoodPoleItem("stripped_fir", TraverseCompatBlocks.STRIPPED_FIR_POLE)

    fun initItems() {
        if (Config.Blocks.poles) {
            registerFuelForEach(arrayOf(STRIPPED_FIR_POLE, FIR_POLE), 300)
        }
    }
}