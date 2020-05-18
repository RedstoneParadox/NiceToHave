package redstoneparadox.nicetohave.block

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView

class FusedWarpedWartBlock: Block(FabricBlockSettings.of(Material.STONE)) {

    @Environment(EnvType.CLIENT)
    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.isOf(this)) true else super.isSideInvisible(state, stateFrom, direction)
    }

    override fun getOpacity(state: BlockState, world: BlockView, pos: BlockPos): Int {
        return world.maxLightLevel
    }
}