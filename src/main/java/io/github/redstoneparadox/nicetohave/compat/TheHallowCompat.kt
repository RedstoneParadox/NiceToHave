package io.github.redstoneparadox.nicetohave.compat

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.item.BlockItem
import io.github.redstoneparadox.nicetohave.block.PoleBlock
import io.github.redstoneparadox.nicetohave.util.initializers.BlocksInitializer
import io.github.redstoneparadox.nicetohave.util.initializers.ItemsInitializer

object TheHallowCompat: ModInitializer {

    override fun onInitialize() {
        return
        /*
        if (FabricLoader.getInstance().isModLoaded("thehallow") && Config.Blocks.poles) {
            Blocks.initBlocks()
            Items.initItems()
        }
        */
    }
    
    object Blocks: BlocksInitializer() {
        val DEADWOOD_POLE: PoleBlock? = registerWoodPole("deadwood", net.minecraft.block.Blocks.OAK_WOOD)
        val STRIPPED_DEADWOOD_POLE: PoleBlock? = registerWoodPole("stripped_deadwood", net.minecraft.block.Blocks.STRIPPED_OAK_WOOD)

        fun initBlocks() {
            registerFlammableBlocks(arrayOf(DEADWOOD_POLE, STRIPPED_DEADWOOD_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }

    object Items: ItemsInitializer() {
        val DEADWOOD_POOL: BlockItem? = registerWoodPoleItem("deadwood", Blocks.DEADWOOD_POLE)
        val STRIPPED_DEADWOOD_POLE: BlockItem? = registerWoodPoleItem("stripped_deadwood", Blocks.STRIPPED_DEADWOOD_POLE)

        fun initItems() {
            registerFuelForEach(arrayOf(DEADWOOD_POOL, STRIPPED_DEADWOOD_POLE), 300)
        }
    }
}