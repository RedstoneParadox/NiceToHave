package redstoneparadox.nicetohave.compat.terrestria

import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.FabricLoader

object TerrestriaCompat : ModInitializer {

    override fun onInitialize() {
        if (FabricLoader.INSTANCE.isModLoaded("terrestria")) {
            TerrestriaCompatBlocks.initBlocks()
        }
    }

}