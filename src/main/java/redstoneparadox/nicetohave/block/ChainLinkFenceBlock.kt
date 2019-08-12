package redstoneparadox.nicetohave.block

import net.minecraft.block.BlockRenderLayer
import net.minecraft.block.FenceBlock

class ChainLinkFenceBlock(settings: Settings) : FenceBlock(settings) {

    /*
    init {
        defaultState = getStateFactory().defaultState.with(IS_POST, true)
    }

    override fun appendProperties(factory: StateFactory.Builder<Block, BlockState>) {
        factory.add(IS_POST)
        super.appendProperties(factory)
    }
     */

    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    companion object {
        //val IS_POST : BooleanProperty = BooleanProperty.of("is_post")
    }
}