package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.ShapeContext
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

class PoleBlock(block : Block) : PillarBlock(FabricBlockSettings.copy(block)), Waterloggable {

    private val shapes : Array<VoxelShape>

    init {
        val verticalShape = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0)
        val northSouth = Block.createCuboidShape(5.0, 5.0, 0.0, 11.0, 11.0, 16.0)
        val eastWest = Block.createCuboidShape(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)

        shapes = arrayOf(verticalShape, northSouth, eastWest)

        defaultState = defaultState.with(WATERLOGGED, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
        super.appendProperties(builder)
    }

    override fun getOutlineShape(state: BlockState, blockView: BlockView?, blockPos: BlockPos?, shapeContext: ShapeContext): VoxelShape {
        return shapes[getShapeIndex(state)]
    }

    override fun getCollisionShape(state: BlockState, blockView: BlockView?, blockPos: BlockPos?, shapeContext: ShapeContext): VoxelShape {
        return shapes[getShapeIndex(state)]
    }

    private fun getShapeIndex(state : BlockState): Int {
        return when(state.get(AXIS)) {
            Direction.Axis.X -> 2
            Direction.Axis.Z-> 1
            Direction.Axis.Y -> 0
            else -> throw NullPointerException()
        }
    }

    override fun getFluidState(blockState_1: BlockState): FluidState {
        return if (blockState_1.get(WATERLOGGED) as Boolean) Fluids.WATER.getStill(false) else super.getFluidState(blockState_1)
    }

    override fun getStateForNeighborUpdate(blockState_1: BlockState, direction_1: Direction?, blockState_2: BlockState?, iWorld_1: WorldAccess?, blockPos_1: BlockPos?, blockPos_2: BlockPos?): BlockState {
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
        return BlockItem(this, Item.Settings().group(ItemGroup.DECORATIONS))
    }

    companion object {
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
    }
}