package net.redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.entity.DispenserBlockEntity;
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

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    @Inject(method = "dispense", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/DispenserBlockEntity;chooseNonEmptySlot()I"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    void dispense(World world_1, BlockPos blockPos_1, CallbackInfo ci, BlockPointerImpl blockPointer, DispenserBlockEntity dispenserBlockEntity_1) {
        Direction direction = world_1.getBlockState(blockPos_1).get(DispenserBlock.FACING);

        if (dispenserBlockEntity_1.isInvEmpty() && world_1.getBlockState(blockPos_1.offset(direction)).getBlock() == Blocks.LADDER && (direction == Direction.UP || direction == Direction.DOWN)) {
            BlockPos lastPos = blockPos_1.offset(direction);
            BlockState initialState = world_1.getBlockState(lastPos);
            int ladderCount = 0;

            while (world_1.getBlockState(lastPos).getBlock() == Blocks.LADDER && world_1.getBlockState(lastPos) == initialState) {
                ladderCount++;
                lastPos = lastPos.offset(direction);
            }

            BlockPos nextPos = lastPos.offset(direction.getOpposite());

            for (int i = ladderCount; i > 0; i--) {
                for (int j = 0; j < 9; j++) {
                    ItemStack invStack = dispenserBlockEntity_1.getInvStack(j);
                    if (invStack.isEmpty()) {
                        dispenserBlockEntity_1.setInvStack(j, new ItemStack(Items.LADDER, 1));
                        break;
                    }
                    else if (invStack.getItem() == Items.LADDER) {
                        if (invStack.getCount() < 64) {
                            invStack.increment(1);
                            break;
                        }
                    }
                }

                world_1.setBlockState(nextPos, Blocks.AIR.getDefaultState());
                nextPos = nextPos.offset(direction.getOpposite());
            }

            playSound(blockPointer);
            spawnParticles(blockPointer, direction);
            ci.cancel();
        }
    }

    private void playSound(BlockPointer blockPointer_1) {
        blockPointer_1.getWorld().playLevelEvent(1000, blockPointer_1.getBlockPos(), 0);
    }

    private void spawnParticles(BlockPointer blockPointer_1, Direction direction_1) {
        blockPointer_1.getWorld().playLevelEvent(2000, blockPointer_1.getBlockPos(), direction_1.getId());
    }
}
