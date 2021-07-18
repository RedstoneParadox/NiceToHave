package io.github.redstoneparadox.nicetohave.client.render.entity.model

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.SkullEntityModel
import net.minecraft.util.Identifier

object NTHEntityModelLayers {
    @JvmStatic
    val SPIDER_HEAD = create("spider_head")
    @JvmStatic
    val CAVE_SPIDER_HEAD = create("cave_spider_head")
    @JvmStatic
    val BLAZE_HEAD = create("blaze_head")

    @Suppress("deprecation", "unstable")
    fun registerLayers() {
        EntityModelLayerRegistry.registerModelLayer(SPIDER_HEAD) { SkullEntityModel.getHeadTexturedModelData() }
        EntityModelLayerRegistry.registerModelLayer(CAVE_SPIDER_HEAD) { SkullEntityModel.getHeadTexturedModelData() }
        EntityModelLayerRegistry.registerModelLayer(BLAZE_HEAD) { SkullEntityModel.getHeadTexturedModelData() }
    }

    private fun create(name: String): EntityModelLayer {
        return EntityModelLayer(Identifier("nicetohave", name), "main")
    }
}