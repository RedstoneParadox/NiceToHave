package io.github.redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import io.github.redstoneparadox.nicetohave.config.Config;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin {

    @Shadow public abstract BoatEntity.Type getBoatType();

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void interact(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable<Boolean> cir) {
        if (Config.Misc.INSTANCE.getVehiclePickup() && playerEntity_1.isSneaking()) {
            Item boatItem = Items.AIR;

            switch (getBoatType()) {
                case OAK:
                    boatItem = Items.OAK_BOAT;
                    break;
                case SPRUCE:
                    boatItem = Items.SPRUCE_BOAT;
                    break;
                case BIRCH:
                    boatItem = Items.BIRCH_BOAT;
                    break;
                case JUNGLE:
                    boatItem = Items.JUNGLE_BOAT;
                    break;
                case ACACIA:
                    boatItem = Items.ACACIA_BOAT;
                    break;
                case DARK_OAK:
                    boatItem = Items.DARK_OAK_BOAT;
                    break;
            }

            if (boatItem != Items.AIR && (playerEntity_1.isCreative() || playerEntity_1.giveItemStack(new ItemStack(boatItem)))) {
                BoatEntity self = ((BoatEntity)(Object)this);
                playerEntity_1.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                self.remove();
                cir.setReturnValue(true);
            }
        }
    }
}
