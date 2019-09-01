package redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.*;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import redstoneparadox.nicetohave.util.config.Config;

import java.util.Random;

@Mixin(KelpBlock.class)
public abstract class KelpBlockMixin extends Block implements Fertilizable {

    @Shadow @Final public static IntProperty AGE;

    public KelpBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }


    @Override
    public boolean isFertilizable(BlockView view, BlockPos pos, BlockState var3, boolean var4) {
        if (!Config.Misc.INSTANCE.getFertilizeMorePlants()) return false;
        return view.getBlockState(pos.up()).getBlock() == Blocks.WATER && view.getBlockState(pos).get(AGE) < 25;
    }

    @Override
    public boolean canGrow(World var1, Random var2, BlockPos var3, BlockState var4) {
        return true;
    }

    @Override
    public void grow(World world, Random random, BlockPos pos, BlockState var4) {
        int age = world.getBlockState(pos).get(AGE);
        world.setBlockState(pos.up(), getDefaultState().with(AGE, age + 1));
    }


}
