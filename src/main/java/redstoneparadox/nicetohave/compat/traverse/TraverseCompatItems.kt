package redstoneparadox.nicetohave.compat.traverse

import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TraverseCompatItems : ItemsInitializer() {

    val FIR_POLE : BlockItem? = registerPoleItem(TraverseCompatBlocks.FIR_POLE, "fir")

    fun initItems() {
        if (Config.getBool("blocks.poles")) {
            registerFuel(FIR_POLE, 300)
        }
    }
}