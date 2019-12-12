package redstoneparadox.nicetohave.compat.terrestria

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Blocks
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import redstoneparadox.nicetohave.util.newconfig.Config

object TerrestriaCompatBlocks : BlocksInitializer() {

    private const val TERRESTRIA : String = "terrestria"

    val REDWOOD_POLE : PoleBlock? = registerWoodPole("redwood", Blocks.OAK_WOOD, TERRESTRIA)
    val HEMLOCK_POLE : PoleBlock? = registerWoodPole("hemlock", Blocks.OAK_WOOD, TERRESTRIA)
    val RUBBER_WOOD_POLE : PoleBlock? = registerWoodPole("rubber", Blocks.OAK_WOOD, TERRESTRIA)
    val CYPRESS_POLE : PoleBlock? = registerWoodPole("cypress", Blocks.OAK_WOOD, TERRESTRIA)
    val WILLOW_POLE : PoleBlock? = registerWoodPole("willow", Blocks.OAK_WOOD, TERRESTRIA)
    val JAPANESE_MAPLE_POLE : PoleBlock? = registerWoodPole("japanese_maple", Blocks.OAK_WOOD, TERRESTRIA)
    val RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerWoodPole("rainbow_eucalyptus", Blocks.OAK_WOOD, TERRESTRIA)
    val SAKURA_POLE : PoleBlock? = registerWoodPole("sakura", Blocks.OAK_WOOD, TERRESTRIA)

    val STRIPPED_REDWOOD_POLE : PoleBlock? = registerWoodPole("stripped_redwood", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_HEMLOCK_POLE : PoleBlock? = registerWoodPole("stripped_hemlock", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_RUBBER_WOOD_POLE : PoleBlock? = registerWoodPole("stripped_rubber", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_CYPRESS_POLE : PoleBlock? = registerWoodPole("stripped_cypress", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_WILLOW_POLE : PoleBlock? = registerWoodPole("stripped_willow", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_JAPANESE_MAPLE_POLE : PoleBlock? = registerWoodPole("stripped_japanese_maple", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_RAINBOW_EUCALYPTUS_POLE : PoleBlock? = registerWoodPole("stripped_rainbow_eucalyptus", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)
    val STRIPPED_SAKURA_POLE : PoleBlock? = registerWoodPole("stripped_sakura", Blocks.STRIPPED_OAK_WOOD, TERRESTRIA)

    fun initBlocks() {
        if (Config.Blocks.poles) {
            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }
}