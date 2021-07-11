package io.github.redstoneparadox.nicetohave.mixin.client.render.entity;

import io.github.redstoneparadox.nicetohave.client.util.EntityRenderersHelper;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EntityRenderers.class)
public abstract class EntityRenderersMixin {
	@Inject(method = "reloadEntityRenderers", at = @At("HEAD"))
	private static void reloadContextListeners(EntityRendererFactory.Context ctx, CallbackInfoReturnable<Map<EntityType<?>, EntityRenderer<?>>> cir) {
		EntityRenderersHelper.reload(ctx);
	}
}
