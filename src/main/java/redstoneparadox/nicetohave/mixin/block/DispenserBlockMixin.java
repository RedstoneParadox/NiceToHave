package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import redstoneparadox.nicetohave.util.config.Config;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlockMixin {

    @Inject(method = "dispense", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/DispenserBlockEntity;chooseNonEmptySlot()I"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    void dispense(World world_1, BlockPos blockPos_1, CallbackInfo ci, BlockPointerImpl blockPointer, DispenserBlockEntity dispenserBlockEntity_1) {
        if (Config.Misc.INSTANCE.getDispenserLadderPlacement()) {
            return;
        }

        Direction direction = world_1.getBlockState(blockPos_1).get(DispenserBlock.FACING);
        Block block = world_1.getBlockState(blockPos_1.offset(direction)).getBlock();

        if (dispenserBlockEntity_1.isInvEmpty()) {
            if (block == Blocks.LADDER && (direction == Direction.UP || direction == Direction.DOWN)) pickupBlocks(Items.LADDER, Blocks.LADDER, direction, blockPos_1, world_1, dispenserBlockEntity_1, blockPointer, ci);
            else if (block == Blocks.SCAFFOLDING && direction == Direction.UP) pickupBlocks(Items.SCAFFOLDING, Blocks.SCAFFOLDING, direction, blockPos_1, world_1, dispenserBlockEntity_1, blockPointer, ci);
        }
    }

    private void pickupBlocks(Item item, Block block, Direction direction, BlockPos startPos, World world, DispenserBlockEntity dispenser, BlockPointer pointer, CallbackInfo ci) {
        BlockPos lastPos = startPos.offset(direction);
        BlockState initialState = world.getBlockState(lastPos);
        int ladderCount = 0;

        while (world.getBlockState(lastPos).getBlock() == block && world.getBlockState(lastPos) == initialState) {
            ladderCount++;
            lastPos = lastPos.offset(direction);
        }

        BlockPos nextPos = lastPos.offset(direction.getOpposite());

        for (int i = ladderCount; i > 0; i--) {
            for (int j = 0; j < 9; j++) {
                ItemStack invStack = dispenser.getInvStack(j);
                if (invStack.isEmpty()) {
                    dispenser.setInvStack(j, new ItemStack(item, 1));
                    break;
                } else if (invStack.getItem() == item) {
                    if (invStack.getCount() < 64) {
                        invStack.increment(1);
                        break;
                    }
                }
            }

            world.setBlockState(nextPos, Blocks.AIR.getDefaultState());
            nextPos = nextPos.offset(direction.getOpposite());
        }

        playSound(pointer);
        spawnParticles(pointer, direction);
        ci.cancel();
    }

    private void playSound(BlockPointer blockPointer_1) {
        blockPointer_1.getWorld().playLevelEvent(1000, blockPointer_1.getBlockPos(), 0);
    }

    private void spawnParticles(BlockPointer blockPointer_1, Direction direction_1) {
        blockPointer_1.getWorld().playLevelEvent(2000, blockPointer_1.getBlockPos(), direction_1.getId());
    }
}
