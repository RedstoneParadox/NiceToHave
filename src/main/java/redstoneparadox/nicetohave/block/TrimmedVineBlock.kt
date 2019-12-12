package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.VineBlock
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import java.util.*

class TrimmedVineBlock : VineBlock(FabricBlockSettings.copy(Blocks.VINE).build()) {

    override fun scheduledTick(blockState_1: BlockState?, serverWorld_1: ServerWorld?, blockPos_1: BlockPos?, random_1: Random?) {
        return
    }

}