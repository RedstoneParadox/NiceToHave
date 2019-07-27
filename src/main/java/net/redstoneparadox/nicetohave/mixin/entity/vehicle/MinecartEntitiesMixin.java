package net.redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.redstoneparadox.nicetohave.item.Items;
import net.redstoneparadox.nicetohave.util.MinecartTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin({MinecartEntity.class})
public abstract class MinecartEntitiesMixin extends AbstractMinecartEntity {

    private AbstractMinecartEntity link = null;

    public MinecartEntitiesMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Override
    public void setVelocity(Vec3d vec3d_1) {
        super.setVelocity(vec3d_1);

        if (link != null) {
            link.setVelocity(vec3d_1);
        }
    }



    @Inject(method = "interact", at = @At("HEAD"))
    private void interact(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable<Boolean> cir) {

        ItemStack handStack = null;

        if (playerEntity_1.isSneaking() && playerEntity_1.getMainHandStack().getItem() == Items.INSTANCE.getCHAIN_LINK()) {
            System.out.println("Main hand!");
            handStack = playerEntity_1.getMainHandStack();
        }
        else if (playerEntity_1.isSneaking() && playerEntity_1.getOffHandStack().getItem() == Items.INSTANCE.getCHAIN_LINK()) {
            System.out.println("Off hand!");
            handStack = playerEntity_1.getOffHandStack();
        }

        if (handStack != null && link == null) {
            if (!playerEntity_1.isCreative()) {
                handStack.decrement(1);
            }

            Optional<AbstractMinecartEntity> newLink = MinecartTracker.INSTANCE.connectCarts(this);
            newLink.ifPresent(abstractMinecartEntity -> link = abstractMinecartEntity);
        }
    }
}
