package redstoneparadox.nicetohave.compat.terrestria

import net.minecraft.item.BlockItem
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer
import redstoneparadox.nicetohave.config.Config

object TerrestriaCompatItems : ItemsInitializer() {

    val REDWOOD_POLE : BlockItem? = registerWoodPoleItem("redwood", TerrestriaCompatBlocks.REDWOOD_POLE)
    val HEMLOCK_POLE : BlockItem? = registerWoodPoleItem("hemlock", TerrestriaCompatBlocks.HEMLOCK_POLE)
    val RUBBER_WOOD_POLE : BlockItem? = registerWoodPoleItem("rubber", TerrestriaCompatBlocks.RUBBER_WOOD_POLE)
    val CYPRESS_POLE : BlockItem? = registerWoodPoleItem("cypress", TerrestriaCompatBlocks.CYPRESS_POLE)
    val WILLOW_POLE : BlockItem? = registerWoodPoleItem("willow", TerrestriaCompatBlocks.WILLOW_POLE)
    val JAPANESE_MAPLE_POLE : BlockItem? = registerWoodPoleItem("japanese_maple", TerrestriaCompatBlocks.JAPANESE_MAPLE_POLE)
    val RAINBOW_EUCALYPTUS_POLE : BlockItem? = registerWoodPoleItem("rainbow_eucalyptus", TerrestriaCompatBlocks.RAINBOW_EUCALYPTUS_POLE)
    val SAKURA_POLE : BlockItem? = registerWoodPoleItem("sakura", TerrestriaCompatBlocks.SAKURA_POLE)

    val STRIPPED_REDWOOD_POLE : BlockItem? = registerWoodPoleItem("stripped_redwood", TerrestriaCompatBlocks.STRIPPED_REDWOOD_POLE)
    val STRIPPED_HEMLOCK_POLE : BlockItem? = registerWoodPoleItem("stripped_hemlock", TerrestriaCompatBlocks.STRIPPED_HEMLOCK_POLE)
    val STRIPPED_RUBBER_WOOD_POLE : BlockItem? = registerWoodPoleItem("stripped_rubber", TerrestriaCompatBlocks.STRIPPED_RUBBER_WOOD_POLE)
    val STRIPPED_CYPRESS_POLE : BlockItem? = registerWoodPoleItem("stripped_cypress", TerrestriaCompatBlocks.STRIPPED_CYPRESS_POLE)
    val STRIPPED_WILLOW_POLE : BlockItem? = registerWoodPoleItem("stripped_willow", TerrestriaCompatBlocks.STRIPPED_WILLOW_POLE)
    val STRIPPED_JAPANESE_MAPLE_POLE : BlockItem? = registerWoodPoleItem("stripped_japanese_maple", TerrestriaCompatBlocks.STRIPPED_JAPANESE_MAPLE_POLE)
    val STRIPPED_RAINBOW_EUCALYPTUS_POLE : BlockItem? = registerWoodPoleItem("stripped_rainbow_eucalyptus", TerrestriaCompatBlocks.STRIPPED_RAINBOW_EUCALYPTUS_POLE)
    val STRIPPED_SAKURA_POLE : BlockItem? = registerWoodPoleItem("stripped_sakura", TerrestriaCompatBlocks.STRIPPED_SAKURA_POLE)

    fun initItems() {
        if (Config.Blocks.poles) {
            registerFuelForEach(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), 300)
            registerFuelForEach(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), 300)
        }
    }
}