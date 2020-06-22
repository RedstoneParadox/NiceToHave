package io.github.redstoneparadox.nicetohave.client.render.entity

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import io.github.redstoneparadox.nicetohave.entity.EntityTypes
import io.github.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import io.github.redstoneparadox.nicetohave.config.Config

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
object EntityRenderers {


    fun registerRenderers() {
        if (Config.Items.dynamite) {
            val factory = EntityRendererRegistry.Factory { manager, context ->  FlyingItemEntityRenderer<ThrownDynamiteEntity>(manager, context.itemRenderer)}
            EntityRendererRegistry.INSTANCE.register(EntityTypes.THROWN_DYNAMITE, factory)
        }
    }
}