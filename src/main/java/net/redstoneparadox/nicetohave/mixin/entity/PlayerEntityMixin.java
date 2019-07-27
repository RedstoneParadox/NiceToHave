package net.redstoneparadox.nicetohave.mixin.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.redstoneparadox.nicetohave.enchantment.Enchantments;
import net.redstoneparadox.nicetohave.enchantment.Flurry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Random;

/**
 * Created by RedstoneParadox on 5/26/2019.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void attack(Entity entity_1, CallbackInfo ci) {
        PlayerEntity self = ((PlayerEntity) (Object) this);

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(self.getMainHandStack());

        //Flurry enchant.
        if (!self.getItemCooldownManager().isCoolingDown(self.getMainHandStack().getItem()) && enchantments.containsKey(Enchantments.INSTANCE.getFLURRY())) {
            int level = enchantments.get(Enchantments.INSTANCE.getFLURRY());
            int doubleStrikeChance = Flurry.Companion.getChance(level);
            Random rand = new Random();

            if (rand.nextInt(100) + 1 <= doubleStrikeChance) {
                self.getAttributeContainer();

            }
        }
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tickMixin(CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity) (Object) this;

    }
}
