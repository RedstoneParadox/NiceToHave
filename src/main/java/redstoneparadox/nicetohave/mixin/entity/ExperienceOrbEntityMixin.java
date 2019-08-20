package redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import redstoneparadox.nicetohave.NiceToHave;
import redstoneparadox.nicetohave.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin {

    @Shadow
    private int amount;

    @Inject(method = "onPlayerCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addExperience(I)V"))
    private void onPlayerCollision(PlayerEntity playerEntity_1, CallbackInfo ci) {
        if (playerEntity_1.hasStatusEffect(StatusEffects.INSTANCE.getINSIGHT())) {
            try {
                int multiplier = (int) ((playerEntity_1.getStatusEffect(StatusEffects.INSTANCE.getINSIGHT()).getAmplifier() + 1) * 1.2);
                playerEntity_1.addExperience(amount * multiplier);
            } catch (NullPointerException e) {
                NiceToHave.INSTANCE.error("Caught NullPointerException while trying to apply effects of Potion of Insight!");
            }
        }
    }
}
