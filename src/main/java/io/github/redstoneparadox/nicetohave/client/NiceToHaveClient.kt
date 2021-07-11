package io.github.redstoneparadox.nicetohave.client

import io.github.redstoneparadox.nicetohave.NiceToHave
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks
import io.github.redstoneparadox.nicetohave.client.networking.ClientPackets
import io.github.redstoneparadox.nicetohave.client.render.entity.EntityRenderers
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.fabricmc.fabric.api.client.model.ModelVariantProvider
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.mixin.`object`.builder.ModelPredicateProviderRegistryAccessor
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.DyeItem
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
class NiceToHaveClient: ClientModInitializer {
    override fun onInitializeClient() {
        NiceToHave.clientOut("Initializing Nice to Have on the Client.")
        EntityRenderers.registerRenderers()
        // ClientPackets.registerPackets()
        ColorProviderRegistry.BLOCK.register(BlockColorProvider { block, pos, world, layer ->
            val provider = ColorProviderRegistry.BLOCK.get(net.minecraft.block.Blocks.VINE)
            return@BlockColorProvider provider?.getColor(block, pos, world, layer) ?: 0xffffff
        }, NiceToHaveBlocks.TRIMMED_VINE)

        ModelPredicateProviderRegistryAccessor.callRegister(Identifier("color")) { stack, world, entity, int ->
            if (entity is PlayerEntity) {
                val mainItem = entity.mainHandStack.item
                val offhandItem = entity.offHandStack.item
                val dye = when {
                    mainItem is DyeItem -> mainItem
                    offhandItem is DyeItem -> offhandItem
                    else -> null
                }
                return@callRegister when (dye?.color) {
                    DyeColor.WHITE -> 0.01f
                    DyeColor.ORANGE -> 0.02f
                    DyeColor.MAGENTA -> 0.03f
                    DyeColor.LIGHT_BLUE -> 0.04f
                    DyeColor.YELLOW -> 0.05f
                    DyeColor.LIME -> 0.06f
                    DyeColor.PINK -> 0.07f
                    DyeColor.GRAY -> 0.08f
                    DyeColor.LIGHT_GRAY -> 0.09f
                    DyeColor.CYAN -> 0.1f
                    DyeColor.PURPLE -> 0.11f
                    DyeColor.BLUE -> 0.12f
                    DyeColor.BROWN -> 0.13f
                    DyeColor.GREEN -> 0.14f
                    DyeColor.RED -> 0.15f
                    DyeColor.BLACK -> 0.16f
                    null -> 0.0f
                }
            }
            return@callRegister 0.0f
        }
    }
}