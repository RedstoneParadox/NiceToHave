package io.github.redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MinecartItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import io.github.redstoneparadox.nicetohave.item.DrinkItem;

/**
 * Created by RedstoneParadox on 5/26/2019.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity /*implements AttackTicksGetSet*/ {

    /*
    @Shadow
    protected int lastAttackedTicks;
     */

    public LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    /*
    @Override
    public void setLastAttackTicks(int ticks) {
        lastAttackedTicks = ticks;
    }

    @Override
    public int getLastAttackTicks() {
        return lastAttackedTicks;
    }
     */

    @Inject(method = "onEquipStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V", shift = At.Shift.BEFORE), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onEquipStack(ItemStack itemStack_1, CallbackInfo ci, SoundEvent soundEvent_1) {
        Item item = itemStack_1.getItem();
        if (item instanceof BoatItem || item instanceof MinecartItem) {
            ci.cancel();
        }
    }

    @Inject(method = "getEatSound", at = @At("HEAD"), cancellable = true)
    private void getEatSound(ItemStack itemStack_1, CallbackInfoReturnable<SoundEvent> cir) {
        if (itemStack_1.getItem() instanceof DrinkItem) {
            cir.setReturnValue(SoundEvents.ENTITY_GENERIC_DRINK);
            cir.cancel();
        }
    }
}
