package redstoneparadox.nicetohave.compat.terrestria

import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TerrestriaCompatItems : ItemsInitializer() {

    val REDWOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.REDWOOD_POLE, "redwood")
    val HEMLOCK_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.HEMLOCK_POLE, "hemlock")
    val RUBBER_WOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.RUBBER_WOOD_POLE, "rubber")
    val CYPRESS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.CYPRESS_POLE, "cypress")
    val WILLOW_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.WILLOW_POLE, "willow")
    val JAPANESE_MAPLE_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.JAPANESE_MAPLE_POLE, "japanese_maple")
    val RAINBOW_EUCALYPTUS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.RAINBOW_EUCALYPTUS_POLE, "rainbow_eucalyptus")
    val SAKURA_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.SAKURA_POLE, "sakura")

    val STRIPPED_REDWOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_REDWOOD_POLE, "stripped_redwood")
    val STRIPPED_HEMLOCK_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_HEMLOCK_POLE, "stripped_hemlock")
    val STRIPPED_RUBBER_WOOD_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_RUBBER_WOOD_POLE, "stripped_rubber")
    val STRIPPED_CYPRESS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_CYPRESS_POLE, "stripped_cypress")
    val STRIPPED_WILLOW_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_WILLOW_POLE, "stripped_willow")
    val STRIPPED_JAPANESE_MAPLE_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_JAPANESE_MAPLE_POLE, "stripped_japanese_maple")
    val STRIPPED_RAINBOW_EUCALYPTUS_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_RAINBOW_EUCALYPTUS_POLE, "stripped_rainbow_eucalyptus")
    val STRIPPED_SAKURA_POLE : BlockItem? = registerPoleItem(TerrestriaCompatBlocks.STRIPPED_SAKURA_POLE, "stripped_sakura")

    fun initItems() {
        if (Config.getBool("blocks.poles")) {
            registerFuelForEach(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), 300)
            registerFuelForEach(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), 300)
        }
    }
}