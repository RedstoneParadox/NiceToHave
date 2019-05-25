package net.redstoneparadox.nicetohave.mixin.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.resource.ReloadableResourceManager;
import net.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(TextureManager textureManager_1, ItemRenderer itemRenderer_1, ReloadableResourceManager reloadableResourceManager_1, CallbackInfo info) {
        EntityRenderDispatcher self = ((EntityRenderDispatcher) (Object) this);
        register(ThrownDynamiteEntity.class, new FlyingItemEntityRenderer<>(self, itemRenderer_1));
    }

    @Shadow
    private <T extends Entity> void register(Class<T> class_1, EntityRenderer<? super T> entityRenderer_1) {

    }
}
