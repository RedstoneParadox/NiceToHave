package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import redstoneparadox.nicetohave.util.config.Config;

import java.util.Random;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin implements Fertilizable {

    @Shadow @Final public static IntProperty AGE;

    @Override
    public boolean isFertilizable(BlockView view, BlockPos pos, BlockState state, boolean var4) {
        return Config.Misc.INSTANCE.getFertilizeMorePlants() &&  state.get(AGE) < 3;
    }

    @Override
    public boolean canGrow(World var1, Random var2, BlockPos var3, BlockState var4) {
        return true;
    }

    @Override
    public void grow(World world, Random random, BlockPos pos, BlockState state) {
        if (random.nextFloat() <= 0.75f) world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
    }
}
