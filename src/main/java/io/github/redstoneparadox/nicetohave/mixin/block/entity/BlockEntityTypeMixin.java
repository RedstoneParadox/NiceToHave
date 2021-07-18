package io.github.redstoneparadox.nicetohave.mixin.block.entity;

import com.mojang.datafixers.types.Type;
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
	@Shadow @Final private static Logger LOGGER;

	@Inject(method = "create", at = @At("HEAD"), cancellable = true)
	private static <T extends BlockEntity> void addCustomSkulls(String string, BlockEntityType.Builder<T> builder, CallbackInfoReturnable<BlockEntityType<T>> cir) {
		if (string.equals("skull")) {
			// Sorry not sorry.
			FabricBlockEntityTypeBuilder<SkullBlockEntity> fabricBuilder = FabricBlockEntityTypeBuilder
					.create(SkullBlockEntity::new,
							Blocks.SKELETON_SKULL,
							Blocks.SKELETON_WALL_SKULL,
							Blocks.CREEPER_HEAD,
							Blocks.CREEPER_WALL_HEAD,
							Blocks.DRAGON_HEAD,
							Blocks.DRAGON_WALL_HEAD,
							Blocks.ZOMBIE_HEAD,
							Blocks.ZOMBIE_WALL_HEAD,
							Blocks.WITHER_SKELETON_SKULL,
							Blocks.WITHER_SKELETON_WALL_SKULL,
							Blocks.PLAYER_HEAD,
							Blocks.PLAYER_WALL_HEAD,
							// Custom skulls
							NiceToHaveBlocks.INSTANCE.getSPIDER_HEAD(),
							NiceToHaveBlocks.INSTANCE.getSPIDER_WALL_HEAD(),
							NiceToHaveBlocks.INSTANCE.getCAVE_SPIDER_HEAD(),
							NiceToHaveBlocks.INSTANCE.getCAVE_SPIDER_WALL_HEAD(),
							NiceToHaveBlocks.INSTANCE.getBLAZE_HEAD(),
							NiceToHaveBlocks.INSTANCE.getBLAZE_WALL_HEAD()
					);

			Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, string);
			BlockEntityType<T> blockEntityType = (BlockEntityType<T>) Registry.register(Registry.BLOCK_ENTITY_TYPE, string, fabricBuilder.build(type));
			cir.setReturnValue(blockEntityType);
		}
	}
}
