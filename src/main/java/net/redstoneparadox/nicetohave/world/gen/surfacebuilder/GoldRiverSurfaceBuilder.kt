package net.redstoneparadox.nicetohave.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*
import kotlin.reflect.KFunction1

class GoldRiverSurfaceBuilder(function: KFunction1<Dynamic<*>, TernarySurfaceConfig>) : SurfaceBuilder<TernarySurfaceConfig>(function) {

    override fun generate(random: Random, chunk: Chunk, var3: Biome?, x: Int, z: Int, y: Int, var7: Double, var9: BlockState?, var10: BlockState?, var11: Int, var12: Long, var14: TernarySurfaceConfig?) {
        SurfaceBuilder.DEFAULT.generate(random, chunk, var3, x, z, y, var7, var9, var10, var11, var12, var14)

        val realX = x and 15
        val realZ = z and 15
        var realY = y;

        while (realY > 0) {
            val pos = BlockPos(realX, realY, realZ)
            if (chunk.getFluidState(pos.up()).fluid == Fluids.WATER) {
                if (random.nextInt(100) + 1 <= 30) chunk.setBlockState(pos, Blocks.GOLD_ORE.defaultState, true)
            }

            realY--
        }
    }
}