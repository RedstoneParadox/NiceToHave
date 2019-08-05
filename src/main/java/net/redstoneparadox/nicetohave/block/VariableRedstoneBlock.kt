package net.redstoneparadox.nicetohave.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.RedstoneBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateFactory
import net.minecraft.state.property.IntProperty
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

/**
 * Created by RedstoneParadox on 7/30/2019.
 */
class VariableRedstoneBlock(settings: Settings) : RedstoneBlock(settings) {

    init {
        defaultState = getStateFactory().defaultState.with(POWER_LEVEL, 0)
    }

    override fun appendProperties(factory: StateFactory.Builder<Block, BlockState>) {
        factory.add(POWER_LEVEL)
    }

    override fun activate(blockState: BlockState, world: World, blockPos: BlockPos, playerEntity: PlayerEntity, hand: Hand, blockHitResult: BlockHitResult): Boolean {

        val currentPower : Int = blockState.get(POWER_LEVEL)

        if (currentPower < 15) {
            world.setBlockState(blockPos, defaultState.with(POWER_LEVEL, currentPower + 1))
        } else {
            world.setBlockState(blockPos, defaultState)
        }

        return true
    }

    override fun getWeakRedstonePower(blockState: BlockState, blockView_1: BlockView?, blockPos_1: BlockPos?, direction_1: Direction?): Int {
        return blockState.get(POWER_LEVEL)
    }

    companion object {
        val POWER_LEVEL : IntProperty = IntProperty.of("power_level", 0, 15)
    }
}