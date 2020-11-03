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

object TraverseCompat : ModInitializer {
    override fun onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("traverse")) {
            CompatBlocks.initBlocks()
            CompatItems.initItems()
        }
    }

    object CompatBlocks : BlocksInitializer() {
        val FIR_POLE : PoleBlock = PoleBlock(Blocks.OAK_WOOD)
        val STRIPPED_FIR_POLE : PoleBlock = PoleBlock(Blocks.STRIPPED_OAK_WOOD)

        fun initBlocks() {
            register("fir_pole", FIR_POLE)
            register("stripped_fir_pole", STRIPPED_FIR_POLE)

            registerFlammableBlocks(arrayOf(FIR_POLE, STRIPPED_FIR_POLE), FlammableBlockRegistry.Entry(5, 20))
        }

        fun mapPolesToStrippedPoles(): Map<Block?, Block?> {
            return mapOf<Block?, Block?>(
                    FIR_POLE to STRIPPED_FIR_POLE
            )
        }
    }

    object CompatItems : ItemsInitializer() {
        val FIR_POLE : BlockItem = poleItem(CompatBlocks.FIR_POLE)
        val STRIPPED_FIR_POLE : BlockItem = poleItem(CompatBlocks.STRIPPED_FIR_POLE)

        fun initItems() {
            register("fir_pole", FIR_POLE)
            register("stripped_fir_pole", STRIPPED_FIR_POLE)

            registerFuelForEach(arrayOf(STRIPPED_FIR_POLE, FIR_POLE), 300)
        }
    }
}