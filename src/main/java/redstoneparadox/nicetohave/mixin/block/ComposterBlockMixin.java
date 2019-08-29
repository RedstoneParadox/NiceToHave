package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import redstoneparadox.nicetohave.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import redstoneparadox.nicetohave.util.config.Config;

import java.util.Random;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {

    @Inject(method = "activate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;setToDefaultPickupDelay()V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1, CallbackInfoReturnable<Boolean> cir, int int_1, ItemStack itemStack_1, float float_1, double double_1, double double_2, double double_3, ItemEntity itemEntity_1) {
        if (Config.Items.INSTANCE.getFertilizer()) {
            ItemStack fertilizerStack = new ItemStack(Items.INSTANCE.getFERTILIZER());
            fertilizerStack.setCount(new Random().nextInt(4) + 1);
            itemEntity_1.setStack(fertilizerStack);
        }
    }

}
