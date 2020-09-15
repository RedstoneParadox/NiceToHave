package io.github.redstoneparadox.nicetohave.compat.traverse

import net.minecraft.item.BlockItem
import io.github.redstoneparadox.nicetohave.util.initializers.ItemsInitializer
import io.github.redstoneparadox.nicetohave.config.Config

object TraverseCompatItems : ItemsInitializer() {

    val FIR_POLE : BlockItem = registerWoodPoleItem("fir", TraverseCompatBlocks.FIR_POLE)
    val STRIPPED_FIR_POLE : BlockItem = registerWoodPoleItem("stripped_fir", TraverseCompatBlocks.STRIPPED_FIR_POLE)

    fun initItems() {
        registerFuelForEach(arrayOf(STRIPPED_FIR_POLE, FIR_POLE), 300)
    }
}