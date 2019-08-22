package redstoneparadox.nicetohave.compat.traverse

import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader

object TraverseCompat : ModInitializer {

    override fun onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("terrestria")) {
            TraverseCompatBlocks.initBlocks()
            TraverseCompatItems.initItems()
        }
    }


}