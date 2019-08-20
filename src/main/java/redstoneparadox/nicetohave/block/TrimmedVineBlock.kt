package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.VineBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class TrimmedVineBlock : VineBlock(FabricBlockSettings.copy(Blocks.VINE).build()) {

    override fun onScheduledTick(blockState_1: BlockState?, world_1: World?, blockPos_1: BlockPos?, random_1: Random?) {
        return
    }


}