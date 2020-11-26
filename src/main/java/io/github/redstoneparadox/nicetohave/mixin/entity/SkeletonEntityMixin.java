package io.github.redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkeletonEntity.class)
public abstract class SkeletonEntityMixin extends AbstractSkeletonEntity {
	protected SkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void onDeath(DamageSource damageSource) {
		if (hasStatusEffect(StatusEffects.WITHER)) {
			method_29243(EntityType.WITHER_SKELETON, true);
			return;
		}

		super.onDeath(damageSource);
	}
}
