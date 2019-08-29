package redstoneparadox.nicetohave.compat.terrestria

import com.terraformersmc.terrestria.init.TerrestriaBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object TerrestriaCompatBlocks : BlocksInitializer() {

    private const val TERRESTRIA : String = "terrestria"

    val REDWOOD_POLE : PoleBlock? = registerPole(TerrestriaBlocks.REDWOOD.wood, "redwood", TERRESTRIA)
    val HEMLOCK_POLE : PoleBlock? = registerPole(TerrestriaBlocks.HEMLOCK.wood, "hemlock", TERRESTRIA)
    val RUBBER_WOOD_POLE : PoleBlock? = registerPole(TerrestriaBlocks.RUBBER.wood, "rubber", TERRESTRIA)
    val CYPRESS_POLE : PoleBlock? = registerPole(TerrestriaBlocks.CYPRESS.wood, "cypress", TERRESTRIA)
    val WILLOW_POLE : PoleBlock? = registerPole(TerrestriaBlocks.WILLOW.wood, "willow", TERRESTRIA)
    val JAPANESE_MAPLE_POLE : PoleBlock? = registerPole(TerrestriaBlocks.JAPANESE_MAPLE.wood, "japanese_maple", TERRESTRIA)
    val RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerPole(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood, "rainbow_eucalyptus", TERRESTRIA)
    val SAKURA_POLE : PoleBlock? = registerPole(TerrestriaBlocks.SAKURA.wood, "sakura", TERRESTRIA)

    val STRIPPED_REDWOOD_POLE : PoleBlock? = registerPole(TerrestriaBlocks.REDWOOD.strippedWood, "stripped_redwood", TERRESTRIA)
    val STRIPPED_HEMLOCK_POLE : PoleBlock? = registerPole(TerrestriaBlocks.HEMLOCK.strippedWood, "stripped_hemlock", TERRESTRIA)
    val STRIPPED_RUBBER_WOOD_POLE : PoleBlock? = registerPole(TerrestriaBlocks.RUBBER.strippedWood, "stripped_rubber", TERRESTRIA)
    val STRIPPED_CYPRESS_POLE : PoleBlock? = registerPole(TerrestriaBlocks.CYPRESS.strippedWood, "stripped_cypress", TERRESTRIA)
    val STRIPPED_WILLOW_POLE : PoleBlock? = registerPole(TerrestriaBlocks.WILLOW.strippedWood, "stripped_willow", TERRESTRIA)
    val STRIPPED_JAPANESE_MAPLE_POLE : PoleBlock? = registerPole(TerrestriaBlocks.JAPANESE_MAPLE.strippedWood, "stripped_japanese_maple", TERRESTRIA)
    val STRIPPED_RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerPole(TerrestriaBlocks.RAINBOW_EUCALYPTUS.strippedWood, "stripped_rainbow_eucalyptus", TERRESTRIA)
    val STRIPPED_SAKURA_POLE : PoleBlock? = registerPole(TerrestriaBlocks.SAKURA.strippedWood, "stripped_sakura", TERRESTRIA)

    fun initBlocks() {
        if (Config.Blocks.poles) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}