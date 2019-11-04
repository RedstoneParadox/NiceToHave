package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.RedstoneBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
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
class AnalogRedstoneEmitterBlock() : RedstoneBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build()) {

    init {
        defaultState = getStateFactory().defaultState.with(POWER_LEVEL, 0)
    }

    override fun appendProperties(factory: StateManager.Builder<Block, BlockState>) {
        factory.add(POWER_LEVEL)
    }

    override fun activate(blockState: BlockState, world: World, blockPos: BlockPos, playerEntity: PlayerEntity, hand: Hand, blockHitResult: BlockHitResult): Boolean {
        if (playerEntity.isSneaking) {
            val power = blockState[POWER_LEVEL]
            val newPower = if (power == 0) 15 else power - 1
            world.setBlockState(blockPos, blockState.with(POWER_LEVEL, newPower))
        }
        else {
            world.setBlockState(blockPos, blockState.cycle(POWER_LEVEL))
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