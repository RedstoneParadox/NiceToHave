package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.block.Waterloggable
import net.minecraft.entity.EntityContext
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.IWorld
import java.lang.NullPointerException

class PoleBlock(val block : Block) : PillarBlock(FabricBlockSettings.copy(block).build()), Waterloggable {

    private val shapes : Array<VoxelShape>

    init {
        val verticalShape = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0)
        val northSouth = Block.createCuboidShape(5.0, 5.0, 0.0, 11.0, 11.0, 16.0)
        val eastWest = Block.createCuboidShape(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)

        shapes = arrayOf(verticalShape, northSouth, eastWest)

        defaultState = defaultState.with(WATERLOGGED, false)
    }

    override fun appendProperties(builder: StateFactory.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
        super.appendProperties(builder)
    }

    override fun getOutlineShape(state: BlockState, blockView_1: BlockView?, blockPos_1: BlockPos?, entityContext_1: EntityContext?): VoxelShape {
        return shapes[getShapeIndex(state)]
    }

    override fun getCollisionShape(state: BlockState, blockView_1: BlockView?, blockPos_1: BlockPos?, entityContext_1: EntityContext?): VoxelShape {
        return shapes[getShapeIndex(state)]
    }

    private fun getShapeIndex(state : BlockState): Int {
        return when(state.get(AXIS)) {
            Direction.Axis.field_11048 -> 2
            Direction.Axis.field_11051 -> 1
            Direction.Axis.field_11052 -> 0
            else -> throw NullPointerException()
        }
    }

    override fun getFluidState(blockState_1: BlockState): FluidState {
        return if (blockState_1.get(WATERLOGGED) as Boolean) Fluids.WATER.getStill(false) else super.getFluidState(blockState_1)
    }

    override fun getStateForNeighborUpdate(blockState_1: BlockState, direction_1: Direction?, blockState_2: BlockState?, iWorld_1: IWorld?, blockPos_1: BlockPos?, blockPos_2: BlockPos?): BlockState {
        if (blockState_1.get(WATERLOGGED) as Boolean) {
            iWorld_1!!.fluidTickScheduler.schedule(blockPos_1, Fluids.WATER, Fluids.WATER.getTickRate(iWorld_1))
        }

        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2)
    }

    override fun getPlacementState(itemPlacementContext_1: ItemPlacementContext): BlockState? {
        val fluidState_1 = itemPlacementContext_1.world.getFluidState(itemPlacementContext_1.blockPos)
        val normalState = super.getPlacementState(itemPlacementContext_1)
        return normalState!!.with(WATERLOGGED, fluidState_1.fluid === Fluids.WATER)
    }

    public fun createBlockItem(): BlockItem {
        return BlockItem(block, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    companion object {
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
    }
}