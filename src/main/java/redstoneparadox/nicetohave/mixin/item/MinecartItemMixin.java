package redstoneparadox.nicetohave.mixin.item;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import redstoneparadox.nicetohave.util.MinecartTracker;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin(MinecartItem.class)
public abstract class MinecartItemMixin {

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    public void useOnBlock(ItemUsageContext itemUsageContext_1, CallbackInfoReturnable<ActionResult> cir, AbstractMinecartEntity abstractMinecartEntity_1) {
        MinecartTracker.INSTANCE.addCart(abstractMinecartEntity_1);
    }
}
