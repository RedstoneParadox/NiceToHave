package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
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

    @Inject(method = "emptyFullComposter", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;setToDefaultPickupDelay()V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void emptyFullComposter(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<BlockState> cir, float f, double d, double e, double g, ItemEntity itemEntity) {
        if (Config.Items.INSTANCE.getFertilizer()) {
            ItemStack fertilizerStack = new ItemStack(NiceToHaveItems.INSTANCE.getFERTILIZER());
            fertilizerStack.setCount(state.get(FERTILIZER_COUNT));
            itemEntity.setStack(fertilizerStack);
        }
    }

    @Inject(method = "emptyComposter", at = @At("TAIL"))
    private static void emptyComposter(BlockState state, WorldAccess world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        world.setBlockState(pos, state.with(FERTILIZER_COUNT, 0).cycle(LEVEL), 3);
    }

    @Inject(method = "scheduledTick", at = @At(value = "HEAD"), cancellable = true)
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (Config.Items.INSTANCE.getFertilizer() && state.get(LEVEL) >= 7 && state.get(FERTILIZER_COUNT) == 0) {
            world.setBlockState(pos, state.with(FERTILIZER_COUNT, new Random().nextInt(4) + 1).cycle(LEVEL));
            world.playSound(null, pos, SoundEvents.BLOCK_COMPOSTER_READY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ci.cancel();
        }
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(FERTILIZER_COUNT);
    }

    @Inject(method = "getInventory", at = @At(value = "RETURN", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
    private void getInventory(BlockState state, WorldAccess world, BlockPos pos, CallbackInfoReturnable<SidedInventory> cir) {
        int fertilizerCount = state.get(FERTILIZER_COUNT);
        if (fertilizerCount > 0) {
            ItemStack stack = new ItemStack(NiceToHaveItems.INSTANCE.getFERTILIZER());
            cir.setReturnValue(new Inventories.FullComposterInventory(state, world, pos, stack, fertilizerCount == 1));
        }
        cir.cancel();
    }

}
