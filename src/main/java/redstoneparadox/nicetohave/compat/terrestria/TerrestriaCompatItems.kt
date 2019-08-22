package redstoneparadox.nicetohave.compat.terrestria

import net.fabricmc.loader.FabricLoader
import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TerrestriaCompatItems : ItemsInitializer() {

    val REDWOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.REDWOOD_POLE, "redwood")
    val HEMLOCK_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.HEMLOCK_POLE, "hemlock")
    val RUBBER_WOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.RUBBER_WOOD_POLE, "rubber_wood")
    val CYPRESS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.CYPRESS_POLE, "cypress")
    val WILLOW_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.WILLOW_POLE, "willow")
    val JAPANESE_MAPLE_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.JAPANESE_MAPLE_POLE, "japanese_maple")
    val RAINBOW_EUCALYPTUS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.RAINBOW_EUCALYPTUS_POLE, "rainbow_eucalyptus")
    val SAKURA_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.SAKURA_POLE, "sakura")

    fun initItems() {
        if (Config.getBool("blocks.poles")) {
            registerFuelForEach(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), 300)
        }
    }
}