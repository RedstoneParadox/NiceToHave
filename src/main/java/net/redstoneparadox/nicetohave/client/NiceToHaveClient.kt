package net.redstoneparadox.nicetohave.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry
import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ExtendedBlockView
import net.redstoneparadox.nicetohave.NiceToHave
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.client.networking.ClientPackets
import net.redstoneparadox.nicetohave.client.render.entity.EntityRenderers

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
            }, Blocks.TRIMMED_VINE_BLOCK)
    }

}