package io.github.redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import io.github.redstoneparadox.nicetohave.config.Config;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow public abstract BoatEntity.Type getBoatType();

    public BoatEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void interact(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable<Boolean> cir) {
        if (Config.Misc.INSTANCE.getVehiclePickup() && playerEntity_1.isSneaking()) {
            Item boatItem = switch (getBoatType()) {
                case OAK -> Items.OAK_BOAT;
                case SPRUCE -> Items.SPRUCE_BOAT;
                case BIRCH -> Items.BIRCH_BOAT;
                case JUNGLE -> Items.JUNGLE_BOAT;
                case ACACIA -> Items.ACACIA_BOAT;
                case DARK_OAK -> Items.DARK_OAK_BOAT;
            };

            if ((playerEntity_1.isCreative() || playerEntity_1.giveItemStack(new ItemStack(boatItem)))) {
                playerEntity_1.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                discard();
                cir.setReturnValue(true);
            }
        }
    }
}
