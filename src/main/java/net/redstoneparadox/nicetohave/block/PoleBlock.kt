package net.redstoneparadox.nicetohave.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.PillarBlock
import net.minecraft.entity.EntityContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import java.lang.NullPointerException

class PoleBlock(settings: Settings?) : PillarBlock(settings) {

    private val shapes : Array<VoxelShape>

    init {
        val verticalShape = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 16.0, 11.0)
        val northSouth = Block.createCuboidShape(5.0, 5.0, 0.0, 11.0, 11.0, 16.0)
        val eastWest = Block.createCuboidShape(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)

        shapes = arrayOf(verticalShape, northSouth, eastWest)
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
}