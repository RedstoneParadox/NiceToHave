package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import redstoneparadox.nicetohave.item.NiceToHaveItems;
import redstoneparadox.nicetohave.misc.Inventories;
import redstoneparadox.nicetohave.config.Config;

import java.util.Random;

import static redstoneparadox.nicetohave.block.Properties.FERTILIZER_COUNT;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {
    @Shadow @Final public static IntProperty LEVEL;

    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;setToDefaultPickupDelay()V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1, CallbackInfoReturnable<Boolean> cir, int int_1, ItemStack itemStack_1, float float_1, double double_1, double double_2, double double_3, ItemEntity itemEntity) {
        if (Config.Items.INSTANCE.getFertilizer()) {
            ItemStack fertilizerStack = new ItemStack(NiceToHaveItems.INSTANCE.getFERTILIZER());
            fertilizerStack.setCount(state.get(FERTILIZER_COUNT));
            itemEntity.setStack(fertilizerStack);
        }
    }

    @Inject(method = "emptyComposter", at = @At("TAIL"))
    private static void emptyComposter(BlockState state, IWorld world, BlockPos pos, CallbackInfo ci) {
        world.setBlockState(pos, state.with(FERTILIZER_COUNT, 0).cycle(LEVEL), 3);
    }

    @Inject(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;scheduledTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", shift = At.Shift.BEFORE))
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.get(LEVEL) >= 7 && state.get(FERTILIZER_COUNT) == 0) {
            world.setBlockState(pos, state.with(FERTILIZER_COUNT, new Random().nextInt(4) + 1).cycle(LEVEL));
        }
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(FERTILIZER_COUNT);
    }

    @Inject(method = "getInventory", at = @At(value = "RETURN", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
    private void getInventory(BlockState state, IWorld world, BlockPos pos, CallbackInfoReturnable<SidedInventory> cir) {
        int fertilizerCount = state.get(FERTILIZER_COUNT);
        if (fertilizerCount > 0) {
            ItemStack stack = new ItemStack(NiceToHaveItems.INSTANCE.getFERTILIZER());
            cir.setReturnValue(new Inventories.FullComposterInventory(state, world, pos, stack, fertilizerCount == 1));
        }
        cir.cancel();
    }

}
