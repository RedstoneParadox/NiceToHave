package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.BlockRenderLayer
import net.minecraft.block.FenceBlock
import net.minecraft.block.Material
import net.minecraft.util.Identifier

class ChainLinkFenceBlock() : FenceBlock(FabricBlockSettings.of(Material.METAL).strength(0.5f, 0.1f).breakByTool(net.minecraft.tag.Tag(Identifier("fabric:pickaxes"))).build()) {

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