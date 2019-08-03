package net.redstoneparadox.nicetohave.block

import net.minecraft.block.BlockRenderLayer
import net.minecraft.block.FenceBlock

class ChainLinkFenceBlock(settings: Settings) : FenceBlock(settings) {

    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }
}