package redstoneparadox.nicetohave.client.render.entity

import net.fabricmc.fabric.api.client.render.EntityRendererRegistry
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import redstoneparadox.nicetohave.entity.ThrownDynamiteEntity

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
object EntityRenderers {


    fun registerRenderers() {
        val factory = EntityRendererRegistry.Factory { manager, context ->  FlyingItemEntityRenderer<ThrownDynamiteEntity>(manager, context.itemRenderer)}
        EntityRendererRegistry.INSTANCE.register(ThrownDynamiteEntity::class.javaObjectType, factory)
    }
}