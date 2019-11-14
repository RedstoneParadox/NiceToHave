package redstoneparadox.nicetohave.compat.terrestria

import com.terraformersmc.terrestria.init.TerrestriaBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object TerrestriaCompatBlocks : BlocksInitializer() {

    private const val TERRESTRIA : String = "terrestria"

    val REDWOOD_POLE : PoleBlock? = registerWoodPole("redwood", TerrestriaBlocks.REDWOOD.wood, TERRESTRIA)
    val HEMLOCK_POLE : PoleBlock? = registerWoodPole("hemlock", TerrestriaBlocks.HEMLOCK.wood, TERRESTRIA)
    val RUBBER_WOOD_POLE : PoleBlock? = registerWoodPole("rubber", TerrestriaBlocks.RUBBER.wood, TERRESTRIA)
    val CYPRESS_POLE : PoleBlock? = registerWoodPole("cypress", TerrestriaBlocks.CYPRESS.wood, TERRESTRIA)
    val WILLOW_POLE : PoleBlock? = registerWoodPole("willow", TerrestriaBlocks.WILLOW.wood, TERRESTRIA)
    val JAPANESE_MAPLE_POLE : PoleBlock? = registerWoodPole("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE.wood, TERRESTRIA)
    val RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerWoodPole("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood, TERRESTRIA)
    val SAKURA_POLE : PoleBlock? = registerWoodPole("sakura", TerrestriaBlocks.SAKURA.wood, TERRESTRIA)

    val STRIPPED_REDWOOD_POLE : PoleBlock? = registerWoodPole("stripped_redwood", TerrestriaBlocks.REDWOOD.strippedWood, TERRESTRIA)
    val STRIPPED_HEMLOCK_POLE : PoleBlock? = registerWoodPole("stripped_hemlock", TerrestriaBlocks.HEMLOCK.strippedWood, TERRESTRIA)
    val STRIPPED_RUBBER_WOOD_POLE : PoleBlock? = registerWoodPole("stripped_rubber", TerrestriaBlocks.RUBBER.strippedWood, TERRESTRIA)
    val STRIPPED_CYPRESS_POLE : PoleBlock? = registerWoodPole("stripped_cypress", TerrestriaBlocks.CYPRESS.strippedWood, TERRESTRIA)
    val STRIPPED_WILLOW_POLE : PoleBlock? = registerWoodPole("stripped_willow", TerrestriaBlocks.WILLOW.strippedWood, TERRESTRIA)
    val STRIPPED_JAPANESE_MAPLE_POLE : PoleBlock? = registerWoodPole("stripped_japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE.strippedWood, TERRESTRIA)
    val STRIPPED_RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerWoodPole("stripped_rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS.strippedWood, TERRESTRIA)
    val STRIPPED_SAKURA_POLE : PoleBlock? = registerWoodPole("stripped_sakura", TerrestriaBlocks.SAKURA.strippedWood, TERRESTRIA)

    fun initBlocks() {
        if (OldConfig.Blocks.poles) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}