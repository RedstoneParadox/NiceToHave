package redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import redstoneparadox.nicetohave.item.NiceToHaveItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "interact", at = @At("HEAD"))
    private void interact(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable<Boolean> cir) {

        if (playerEntity_1.isSneaking() && playerEntity_1.getMainHandStack().getItem() == NiceToHaveItems.INSTANCE.getCHAIN_LINK()) {
            System.out.println("Success!");
            cir.setReturnValue(true);
            cir.cancel();
        }
        else if (playerEntity_1.isSneaking() && playerEntity_1.getOffHandStack().getItem() == NiceToHaveItems.INSTANCE.getCHAIN_LINK()) {
            System.out.println("Success!");
            cir.setReturnValue(true);
            cir.cancel();
        }

    }
}
