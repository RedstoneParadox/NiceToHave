package redstoneparadox.nicetohave.misc;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import org.jetbrains.annotations.Nullable;
import redstoneparadox.nicetohave.block.Properties;
import redstoneparadox.nicetohave.item.NiceToHaveItems;

import static net.minecraft.block.ComposterBlock.LEVEL;

public class Inventories {

    public static class FullComposterInventory extends BasicInventory implements SidedInventory {
        private final BlockState state;
        private final IWorld world;
        private final BlockPos pos;
        private final boolean last;
        private boolean dirty;

        public FullComposterInventory(BlockState state, IWorld world, BlockPos pos, ItemStack outputItem, boolean last) {
            super(outputItem);
            this.state = state;
            this.world = world;
            this.pos = pos;
            this.last = last;
        }

        public int getInvMaxStackAmount() {
            return 64;
        }

        public int[] getInvAvailableSlots(Direction side) {
            return side == Direction.DOWN ? new int[]{0} : new int[0];
        }

        public boolean canInsertInvStack(int slot, ItemStack stack, @Nullable Direction dir) {
            return false;
        }

        public boolean canExtractInvStack(int slot, ItemStack stack, Direction dir) {
            return !this.dirty && dir == Direction.DOWN && stack.getItem() == NiceToHaveItems.INSTANCE.getFERTILIZER();
        }

        public void markDirty() {
            if (last) {
                world.setBlockState(pos, state.with(LEVEL, 0).with(Properties.FERTILIZER_COUNT, 0), 3);
            }
            else {
                world.setBlockState(pos, state.with(Properties.FERTILIZER_COUNT, state.get(Properties.FERTILIZER_COUNT) - 1), 3);
            }

            this.dirty = true;
        }
    }
}
