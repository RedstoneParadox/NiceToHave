package io.github.redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
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
import io.github.redstoneparadox.nicetohave.config.Config;

@Mixin(DispenserBlock.class)
public abstract class DispenserBlockMixin {

    @Inject(method = "dispense", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/DispenserBlockEntity;chooseNonEmptySlot()I"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    void dispense(ServerWorld serverWorld, BlockPos blockPos, CallbackInfo ci) {
        if (Config.Redstone.INSTANCE.getDispenserLadderPlacement()) {
            return;
        }

        Direction direction = serverWorld.getBlockState(blockPos).get(DispenserBlock.FACING);
        Block block = serverWorld.getBlockState(blockPos.offset(direction)).getBlock();
        BlockPointerImpl blockPointer = new BlockPointerImpl(serverWorld, blockPos);
        DispenserBlockEntity dispenserBlockEntity = blockPointer.getBlockEntity();

        if (dispenserBlockEntity.isEmpty()) {
            if (block == Blocks.LADDER && (direction == Direction.UP || direction == Direction.DOWN)) pickupBlocks(Items.LADDER, Blocks.LADDER, direction, blockPos, serverWorld, dispenserBlockEntity, blockPointer, ci);
            else if (block == Blocks.SCAFFOLDING && direction == Direction.UP) pickupBlocks(Items.SCAFFOLDING, Blocks.SCAFFOLDING, direction, blockPos, serverWorld, dispenserBlockEntity, blockPointer, ci);
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
                ItemStack invStack = dispenser.getStack(j);
                if (invStack.isEmpty()) {
                    dispenser.setStack(j, new ItemStack(item, 1));
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
        blockPointer_1.getWorld().syncGlobalEvent(1000, blockPointer_1.getPos(), 0);
    }

    private void spawnParticles(BlockPointer blockPointer_1, Direction direction_1) {
        blockPointer_1.getWorld().syncGlobalEvent(2000, blockPointer_1.getPos(), direction_1.getId());
    }
}
