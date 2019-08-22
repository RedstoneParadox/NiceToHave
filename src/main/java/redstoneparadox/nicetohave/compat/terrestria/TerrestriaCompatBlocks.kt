package redstoneparadox.nicetohave.compat.terrestria

import com.terraformersmc.terrestria.init.TerrestriaBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.util.config.Config

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

    fun initBlocks() {
        if (Config.getBool("blocks.poles")) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}