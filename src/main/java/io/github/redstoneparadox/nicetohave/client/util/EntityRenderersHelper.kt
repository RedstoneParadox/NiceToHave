package io.github.redstoneparadox.nicetohave.client.util

import net.minecraft.client.render.entity.EntityRendererFactory

object EntityRenderersHelper {
    private val LISTENERS: MutableList<(EntityRendererFactory.Context) -> Unit> = mutableListOf()

    fun registerContextListener(listener: (EntityRendererFactory.Context) -> Unit) {
        LISTENERS.add(listener)
    }

    @JvmStatic
    fun reload(context: EntityRendererFactory.Context) {
        LISTENERS.forEach { it(context) }
    }
}