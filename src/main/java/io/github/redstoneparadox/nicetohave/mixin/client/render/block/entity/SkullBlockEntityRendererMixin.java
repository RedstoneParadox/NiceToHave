package io.github.redstoneparadox.nicetohave.mixin.client.render.block.entity;

import com.google.common.collect.ImmutableMap;
import io.github.redstoneparadox.nicetohave.block.CustomSkullBlock;
import io.github.redstoneparadox.nicetohave.client.render.entity.model.NTHEntityModelLayers;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public abstract class SkullBlockEntityRendererMixin {
	@Shadow @Final private static Map<SkullBlock.SkullType, Identifier> TEXTURES;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void addCustomSkullTextures(BlockEntityRendererFactory.Context context, CallbackInfo ci) {
		TEXTURES.put(CustomSkullBlock.Type.SPIDER,  new Identifier("textures/entity/spider/spider.png"));
		TEXTURES.put(CustomSkullBlock.Type.CAVE_SPIDER,  new Identifier("textures/entity/spider/cave_spider.png"));
		TEXTURES.put(CustomSkullBlock.Type.BLAZE,  new Identifier("textures/entity/blaze.png"));
	}

	@Inject(method = "getModels", at = @At("TAIL"))
	private static void addCustomSkullModels(EntityModelLoader entityModelLoader, CallbackInfoReturnable<Map<SkullBlock.SkullType, SkullBlockEntityModel>> cir) {
		ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> builder = ImmutableMap.builder();
		builder.putAll(cir.getReturnValue());

		builder.put(CustomSkullBlock.Type.SPIDER, new SkullEntityModel(entityModelLoader.getModelPart(NTHEntityModelLayers.getSPIDER_HEAD())));
		builder.put(CustomSkullBlock.Type.CAVE_SPIDER, new SkullEntityModel(entityModelLoader.getModelPart(NTHEntityModelLayers.getCAVE_SPIDER_HEAD())));
		builder.put(CustomSkullBlock.Type.BLAZE, new SkullEntityModel(entityModelLoader.getModelPart(NTHEntityModelLayers.getBLAZE_HEAD())));
	}
}
