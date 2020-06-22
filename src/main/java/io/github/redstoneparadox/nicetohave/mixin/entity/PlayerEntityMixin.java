package io.github.redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.redstoneparadox.nicetohave.command.CommandConfirmation;
import io.github.redstoneparadox.nicetohave.enchantment.Enchantments;
import io.github.redstoneparadox.nicetohave.enchantment.Flurry;
import io.github.redstoneparadox.nicetohave.hooks.CommandConfirmationHolder;

import java.util.Map;
import java.util.Random;

/**
 * Created by RedstoneParadox on 5/26/2019.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements CommandConfirmationHolder {

    private CommandConfirmation confirmation = null;

    private static final EntityAttributeModifier FLURRY_MODIFIER = new EntityAttributeModifier("b677d79b-3629-4d99-9ab1-b133347ed3c6", 10.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attack(Entity entity_1, CallbackInfo ci) {
        PlayerEntity self = ((PlayerEntity) (Object) this);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(self.getMainHandStack());

        if (enchantments.containsKey(Enchantments.INSTANCE.getFLURRY())) {
            int level = enchantments.get(Enchantments.INSTANCE.getFLURRY());
            double doubleStrikeChance = Flurry.Companion.getChance(level);
            Random rand = new Random();

            if (rand.nextInt(100) + 1 <= doubleStrikeChance) {
                addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 40, 1));
            }
        }

    }


    @Override
    public CommandConfirmation getConfirmation() {
        return confirmation;
    }

    @Override
    public void setCommandConfirmation(CommandConfirmation confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public void clearCommandConfirmation() {
        confirmation = null;
    }
}
