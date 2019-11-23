package redstoneparadox.nicetohave.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.render.RenderLayer
import redstoneparadox.nicetohave.NiceToHave
import redstoneparadox.nicetohave.block.NiceToHaveBlocks
import redstoneparadox.nicetohave.client.networking.ClientPackets
import redstoneparadox.nicetohave.client.render.entity.EntityRenderers

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
class NiceToHaveClient : ClientModInitializer {

    override fun onInitializeClient() {
        NiceToHave.clientOut("It's Nice To Have you on the client!")
        EntityRenderers.registerRenderers()
        ClientPackets.registerPackets()
        ColorProviderRegistry.BLOCK.register(BlockColorProvider { block, pos, world, layer ->
            val provider = ColorProviderRegistry.BLOCK.get(net.minecraft.block.Blocks.VINE)
            return@BlockColorProvider provider.getColor(block, pos, world, layer)
        }, NiceToHaveBlocks.TRIMMED_VINE)

        if (NiceToHaveBlocks.CHAIN_LINK_FENCE != null) BlockRenderLayerMap.INSTANCE.putBlock(NiceToHaveBlocks.CHAIN_LINK_FENCE, RenderLayer.getCutout())
    }

}