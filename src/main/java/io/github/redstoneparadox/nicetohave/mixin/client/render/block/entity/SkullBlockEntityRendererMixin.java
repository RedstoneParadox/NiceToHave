package io.github.redstoneparadox.nicetohave.mixin.client.render.block.entity;

import io.github.redstoneparadox.nicetohave.block.CustomSkullBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public abstract class SkullBlockEntityRendererMixin {
	@Shadow @Final private Map<SkullBlock.SkullType, SkullEntityModel> MODELS;
	@Shadow @Final private static Map<SkullBlock.SkullType, Identifier> TEXTURES;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void addCustomSkullTextures(BlockEntityRendererFactory.Context context, CallbackInfo ci) {
		SkullEntityModel skullEntityModel = new SkullEntityModel(0, 0, 64, 32);
		// SkullEntityModel skullEntityModel2 = new SkullOverlayEntityModel();
		SkullEntityModel spiderSkullEntityModel = new SkullEntityModel(32, 4, 64, 32);

		MODELS.put(CustomSkullBlock.Type.SPIDER, spiderSkullEntityModel);
		MODELS.put(CustomSkullBlock.Type.CAVE_SPIDER, spiderSkullEntityModel);
		MODELS.put(CustomSkullBlock.Type.BLAZE, skullEntityModel);

		TEXTURES.put(CustomSkullBlock.Type.SPIDER,  new Identifier("textures/entity/spider/spider.png"));
		TEXTURES.put(CustomSkullBlock.Type.CAVE_SPIDER,  new Identifier("textures/entity/spider/cave_spider.png"));
		TEXTURES.put(CustomSkullBlock.Type.BLAZE,  new Identifier("textures/entity/blaze.png"));
	}
}
