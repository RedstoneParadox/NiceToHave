package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneBlockMixin implements Fertilizable {

    @Shadow
    public abstract boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1);

    @Override
    public boolean isFertilizable(BlockView view, BlockPos pos, BlockState state, boolean isClient) {
        World world = (World)view;
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
            return false;
        }
        else {
            if (countSugarCane(world, pos, Direction.UP) + countSugarCane(world, pos, Direction.DOWN) >= 2) {
                return false;
            }
            BlockPos topPos = findTop(world, pos);

            return world.getBlockState(topPos.up()).getBlock() == Blocks.AIR;
        }
    }

    @Override
    public boolean canGrow(World var1, Random var2, BlockPos var3, BlockState var4) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, BlockState state) {
        BlockPos topPos = findTop(world, pos);
        world.setBlockState(topPos.up(), Blocks.SUGAR_CANE.getDefaultState());
    }

    private int countSugarCane(World world, BlockPos pos, Direction dir) {
        int count = 0;
        BlockPos nextPos = pos.offset(dir);

        while (world.getBlockState(nextPos).getBlock() == Blocks.SUGAR_CANE) {
            count += 1;
            nextPos = nextPos.offset(dir);
        }

        return count;
    }

    private BlockPos findTop(World world, BlockPos pos) {
        BlockPos topPos = pos;

        while (world.getBlockState(topPos.up()).getBlock() == Blocks.SUGAR_CANE) {
            topPos = topPos.up();
        }
        return topPos;
    }
}
