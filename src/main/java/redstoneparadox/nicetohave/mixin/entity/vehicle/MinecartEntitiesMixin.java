package redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import redstoneparadox.nicetohave.util.config.Config;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin({MinecartEntity.class, CommandBlockMinecartEntity.class, StorageMinecartEntity.class, FurnaceMinecartEntity.class})
public abstract class MinecartEntitiesMixin extends AbstractMinecartEntity {

    /*
    private AbstractMinecartEntity link = null;
     */

    private MinecartEntitiesMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    /*
    @Override
    public void setVelocity(Vec3d vec3d_1) {
        super.setVelocity(vec3d_1);

        if (link != null) {
            link.setVelocity(vec3d_1);
        }
    }
     */



    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void interact(PlayerEntity playerEntity_1, Hand hand_1, CallbackInfoReturnable<Boolean> cir) {
        AbstractMinecartEntity self = ((AbstractMinecartEntity)(Object)this);

        if (Config.INSTANCE.getBool("misc.vehicle_pickup", true) && playerEntity_1.isSneaking()) {
            Item minecartItem = net.minecraft.item.Items.AIR;

            switch (self.getMinecartType()) {
                case RIDEABLE:
                    minecartItem = net.minecraft.item.Items.MINECART;
                    break;
                case CHEST:
                    minecartItem = net.minecraft.item.Items.CHEST_MINECART;
                    break;
                case FURNACE:
                    minecartItem = net.minecraft.item.Items.FURNACE_MINECART;
                    break;
                case HOPPER:
                    minecartItem = net.minecraft.item.Items.HOPPER_MINECART;
                    break;
                case COMMAND_BLOCK:
                    if (playerEntity_1.isCreative()) minecartItem = net.minecraft.item.Items.COMMAND_BLOCK_MINECART;
                    break;
            }

            if (minecartItem != net.minecraft.item.Items.AIR && (playerEntity_1.isCreative() || playerEntity_1.giveItemStack(new ItemStack(minecartItem)))) {
                playerEntity_1.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                remove();
                cir.setReturnValue(true);
                cir.cancel();
            }
        }

        /*
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
         */
    }
}
