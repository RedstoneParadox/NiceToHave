package net.redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.redstoneparadox.nicetohave.util.MinecartTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin {


    @Inject(method = "dropItems", at = @At("HEAD"))
    private void dropItems(DamageSource damageSource_1, CallbackInfo ci) {
        AbstractMinecartEntity self = ((AbstractMinecartEntity) (Object) this);

        MinecartTracker.INSTANCE.removeCart(self);
    }
}
