package redstoneparadox.nicetohave.compat.terrestria

import com.terraformersmc.terrestria.init.TerrestriaBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.util.config.Config

object TerrestriaCompatBlocks : BlocksInitializer() {

    val REDWOOD_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.REDWOOD.wood), "redwood_pole", "blocks.poles")
    val HEMLOCK_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.HEMLOCK.wood), "hemlock_pole", "blocks.poles")
    val RUBBER_WOOD_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.RUBBER.wood), "rubber_wood_pole", "blocks.poles")
    val CYPRESS_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.CYPRESS.wood), "cypress_pole", "blocks.poles")
    val WILLOW_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.WILLOW.wood), "willow_pole", "blocks.poles")
    val JAPANESE_MAPLE_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.JAPANESE_MAPLE.wood), "japanese_maple_pole", "blocks.pole")
    val RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerPole(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood, "rainbow_eucalyptus")
    val SAKURA_POLE : PoleBlock? = register(PoleBlock(TerrestriaBlocks.SAKURA.wood), "sakura_pole", "blocks.pole")

    fun initBlocks() {
        if (Config.getBool("blocks.pole")) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}