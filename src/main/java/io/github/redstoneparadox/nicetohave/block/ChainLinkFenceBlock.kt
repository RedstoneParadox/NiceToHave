package io.github.redstoneparadox.nicetohave.block


import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.FenceBlock
import net.minecraft.block.Material

class ChainLinkFenceBlock() : FenceBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f)) {

    /*
    init {
        defaultState = getStateFactory().defaultState.with(IS_POST, true)
    }

    override fun appendProperties(factory: StateFactory.Builder<Block, BlockState>) {
        factory.add(IS_POST)
        super.appendProperties(factory)
    }
     */

    companion object {
        //val IS_POST : BooleanProperty = BooleanProperty.of("is_post")
    }
}