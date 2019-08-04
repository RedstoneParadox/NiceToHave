package net.redstoneparadox.nicetohave.mixin.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.redstoneparadox.nicetohave.block.TrimmedVineBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public abstract class ShearsItemMixin extends Item {
    public ShearsItemMixin(Settings item$Settings_1) {
        super(item$Settings_1);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
        BlockPos pos = itemUsageContext_1.getBlockPos();
        World world = itemUsageContext_1.getWorld();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.VINE) {
            boolean up = state.get(VineBlock.UP);
            boolean north = state.get(VineBlock.NORTH);
            boolean south = state.get(VineBlock.SOUTH);
            boolean east = state.get(VineBlock.EAST);
            boolean west = state.get(VineBlock.WEST);

            world.setBlockState(pos, net.redstoneparadox.nicetohave.block.Blocks.INSTANCE.getTRIMMED_VINE_BLOCK()
                    .getDefaultState()
                    .with(VineBlock.UP, up)
                    .with(VineBlock.NORTH, north)
                    .with(VineBlock.SOUTH, south)
                    .with(VineBlock.EAST, east)
                    .with(VineBlock.WEST, west)
            );
            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(itemUsageContext_1);
    }
}
