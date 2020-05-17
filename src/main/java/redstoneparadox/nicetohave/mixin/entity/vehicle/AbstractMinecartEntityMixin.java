package redstoneparadox.nicetohave.mixin.entity.vehicle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import redstoneparadox.nicetohave.config.Config;

import static net.minecraft.entity.vehicle.AbstractMinecartEntity.Type.TNT;

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity {

    public AbstractMinecartEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Override
    public boolean interact(PlayerEntity playerEntity_1, Hand hand_1) {
        if (Config.Misc.INSTANCE.getVehiclePickup() && playerEntity_1.isSneaking()) {
            AbstractMinecartEntity self = ((AbstractMinecartEntity)(Object)this);
            Item minecartItem = net.minecraft.item.Items.AIR;

            if (self.getMinecartType() == TNT) {
                TntMinecartEntity tntCartSelf = (TntMinecartEntity) self;
                if (!tntCartSelf.isPrimed()) {
                    minecartItem = Items.TNT_MINECART;
                }
            }

            if (minecartItem != net.minecraft.item.Items.AIR && (playerEntity_1.isCreative() || playerEntity_1.giveItemStack(new ItemStack(minecartItem)))) {
                playerEntity_1.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                remove();
                return true;
            }
        }
        return false;
    }
}
