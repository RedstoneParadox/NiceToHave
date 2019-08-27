package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(KelpPlantBlock.class)
public abstract class KelpPlantBlockMixin extends Block implements Fertilizable {
    public KelpPlantBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public boolean isFertilizable(BlockView view, BlockPos pos, BlockState var3, boolean var4) {
        BlockPos top = findTop(view, pos);
        return view.getBlockState(top).getBlock() == Blocks.KELP && ((Fertilizable) view.getBlockState(top).getBlock()).isFertilizable(view, top, view.getBlockState(top), var4);
    }

    @Override
    public boolean canGrow(World var1, Random var2, BlockPos var3, BlockState var4) {
        return true;
    }

    @Override
    public void grow(World world, Random random, BlockPos pos, BlockState var4) {
        BlockPos top = findTop(world, pos);
        if (world.getBlockState(top).getBlock() == Blocks.KELP) {
            ((Fertilizable) world.getBlockState(top).getBlock()).grow(world, random, top, world.getBlockState(top));
        }
    }

    private BlockPos findTop(BlockView view, BlockPos pos) {
        BlockPos top = pos;
        while (view.getBlockState(top).getBlock() != Blocks.KELP) {
            top = top.up();
        }
        return top;
    }
}
