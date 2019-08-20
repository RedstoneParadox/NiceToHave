package redstoneparadox.nicetohave.compat.terrestria

import com.terraformersmc.terrestria.init.TerrestriaBlocks
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.util.config.Config

object TerrestriaCompatBlocks : BlocksInitializer() {

    val REDWOOD_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.REDWOOD.wood)), "redwood_pole", "blocks.poles")
    val HEMLOCK_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.HEMLOCK.wood)), "hemlock_pole", "blocks.poles")
    val RUBBER_WOOD_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.RUBBER.wood)), "rubber_wood_pole", "blocks.poles")
    val CYPRESS_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.CYPRESS.wood)), "cypress_pole", "blocks.poles")
    val WILLOW_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.WILLOW.wood)), "willow_pole", "blocks.poles")
    val JAPANESE_MAPLE_POLE : Block? = register(PoleBlock(copySettings(TerrestriaBlocks.JAPANESE_MAPLE.wood)), "japanese_maple_pole", "blocks.pole")
    val SAKURA_POLE : Block? = register(PoleBlock(TerrestriaBlocks.SAKURA.wood), "sakura_pole", "blocks.pole")

    fun initBlocks() {
        if (Config.getBool("blocks.pole")) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}