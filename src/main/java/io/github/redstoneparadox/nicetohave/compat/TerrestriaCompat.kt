package io.github.redstoneparadox.nicetohave.compat

import io.github.redstoneparadox.nicetohave.block.PoleBlock
import io.github.redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import io.github.redstoneparadox.nicetohave.util.initializers.ItemsInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem

object TerrestriaCompat : ModInitializer {
    override fun onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("terrestria")) {
            CompatBlocks.initBlocks()
            CompatItems.initItems()
        }
    }

    object CompatBlocks : BlocksInitializer() {
        val REDWOOD_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val HEMLOCK_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val RUBBER_WOOD_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val CYPRESS_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val WILLOW_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val JAPANESE_MAPLE_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val RAINBOW_EUCALYPTUS_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val SAKURA_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)

        val STRIPPED_REDWOOD_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_HEMLOCK_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_RUBBER_WOOD_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_CYPRESS_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_WILLOW_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_JAPANESE_MAPLE_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_RAINBOW_EUCALYPTUS_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)
        val STRIPPED_SAKURA_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)

        fun initBlocks() {
            register("redwood_pole", REDWOOD_POLE)
            register("hemlock_pole", HEMLOCK_POLE)
            register("rubber_wood_pole", RUBBER_WOOD_POLE)
            register("cypress_pole", CYPRESS_POLE)
            register("willow_pole", WILLOW_POLE)
            register("japanese_maple_pole", JAPANESE_MAPLE_POLE)
            register("rainbow_eucalyptus_pole", RAINBOW_EUCALYPTUS_POLE)
            register("sakura_pole", SAKURA_POLE)

            register("stripped_redwood_pole", STRIPPED_REDWOOD_POLE)
            register("stripped_hemlock_pole", STRIPPED_HEMLOCK_POLE)
            register("stripped_rubber_wood_pole", STRIPPED_RUBBER_WOOD_POLE)
            register("stripped_cypress_pole", STRIPPED_CYPRESS_POLE)
            register("stripped_willow_pole", STRIPPED_WILLOW_POLE)
            register("stripped_japanese_maple_pole", STRIPPED_JAPANESE_MAPLE_POLE)
            register("stripped_rainbow_eucalyptus_pole", STRIPPED_RAINBOW_EUCALYPTUS_POLE)
            register("stripped_sakura_pole", STRIPPED_SAKURA_POLE)

            registerFlammableBlocks(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), FlammableBlockRegistry.Entry(5, 20))
        }

        fun mapPolesToStrippedPoles(): Map<Block?, Block?> {
            return mapOf<Block?, Block?>(
                    REDWOOD_POLE to STRIPPED_REDWOOD_POLE,
                    HEMLOCK_POLE to STRIPPED_HEMLOCK_POLE,
                    RUBBER_WOOD_POLE to STRIPPED_RUBBER_WOOD_POLE,
                    CYPRESS_POLE to STRIPPED_CYPRESS_POLE,
                    WILLOW_POLE to STRIPPED_WILLOW_POLE,
                    JAPANESE_MAPLE_POLE to STRIPPED_JAPANESE_MAPLE_POLE,
                    RAINBOW_EUCALYPTUS_POLE to STRIPPED_RAINBOW_EUCALYPTUS_POLE,
                    SAKURA_POLE to STRIPPED_SAKURA_POLE
            )
        }
    }

    object CompatItems : ItemsInitializer() {
        val REDWOOD_POLE : BlockItem = poleItem(CompatBlocks.REDWOOD_POLE)
        val HEMLOCK_POLE : BlockItem = poleItem(CompatBlocks.HEMLOCK_POLE)
        val RUBBER_WOOD_POLE : BlockItem = poleItem(CompatBlocks.RUBBER_WOOD_POLE)
        val CYPRESS_POLE : BlockItem = poleItem(CompatBlocks.CYPRESS_POLE)
        val WILLOW_POLE : BlockItem = poleItem(CompatBlocks.WILLOW_POLE)
        val JAPANESE_MAPLE_POLE : BlockItem = poleItem(CompatBlocks.JAPANESE_MAPLE_POLE)
        val RAINBOW_EUCALYPTUS_POLE : BlockItem = poleItem(CompatBlocks.RAINBOW_EUCALYPTUS_POLE)
        val SAKURA_POLE : BlockItem = poleItem(CompatBlocks.SAKURA_POLE)

        val STRIPPED_REDWOOD_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_REDWOOD_POLE)
        val STRIPPED_HEMLOCK_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_HEMLOCK_POLE)
        val STRIPPED_RUBBER_WOOD_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_RUBBER_WOOD_POLE)
        val STRIPPED_CYPRESS_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_CYPRESS_POLE)
        val STRIPPED_WILLOW_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_WILLOW_POLE)
        val STRIPPED_JAPANESE_MAPLE_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_JAPANESE_MAPLE_POLE)
        val STRIPPED_RAINBOW_EUCALYPTUS_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_RAINBOW_EUCALYPTUS_POLE)
        val STRIPPED_SAKURA_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_SAKURA_POLE)

        fun initItems() {
            register("redwood_pole", REDWOOD_POLE)
            register("hemlock_pole", HEMLOCK_POLE)
            register("rubber_wood_pole", RUBBER_WOOD_POLE)
            register("cypress_pole", CYPRESS_POLE)
            register("willow_pole", WILLOW_POLE)
            register("japanese_maple_pole", JAPANESE_MAPLE_POLE)
            register("rainbow_eucalyptus_pole", RAINBOW_EUCALYPTUS_POLE)
            register("sakura_pole", SAKURA_POLE)

            register("stripped_redwood_pole", STRIPPED_REDWOOD_POLE)
            register("stripped_hemlock_pole", STRIPPED_HEMLOCK_POLE)
            register("stripped_rubber_wood_pole", STRIPPED_RUBBER_WOOD_POLE)
            register("stripped_cypress_pole", STRIPPED_CYPRESS_POLE)
            register("stripped_willow_pole", STRIPPED_WILLOW_POLE)
            register("stripped_japanese_maple_pole", STRIPPED_JAPANESE_MAPLE_POLE)
            register("stripped_rainbow_eucalyptus_pole", STRIPPED_RAINBOW_EUCALYPTUS_POLE)
            register("stripped_sakura_pole", STRIPPED_SAKURA_POLE)

            registerFuelForEach(arrayOf(REDWOOD_POLE, HEMLOCK_POLE, RUBBER_WOOD_POLE, CYPRESS_POLE, WILLOW_POLE, JAPANESE_MAPLE_POLE, RAINBOW_EUCALYPTUS_POLE, SAKURA_POLE), 300)
            registerFuelForEach(arrayOf(STRIPPED_REDWOOD_POLE, STRIPPED_HEMLOCK_POLE, STRIPPED_RUBBER_WOOD_POLE, STRIPPED_CYPRESS_POLE, STRIPPED_WILLOW_POLE, STRIPPED_JAPANESE_MAPLE_POLE, STRIPPED_RAINBOW_EUCALYPTUS_POLE, STRIPPED_SAKURA_POLE), 300)
        }
    }
}