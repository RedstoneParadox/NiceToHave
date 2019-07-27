package net.redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.entity.LivingEntity;
import net.redstoneparadox.nicetohave.hooks.AttackTicksGetSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by RedstoneParadox on 5/26/2019.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements AttackTicksGetSet {

    @Shadow
    protected int lastAttackedTicks;

    @Override
    public void setLastAttackTicks(int ticks) {
        lastAttackedTicks = ticks;
    }

    @Override
    public int getLastAttackTicks() {
        return lastAttackedTicks;
    }
}
