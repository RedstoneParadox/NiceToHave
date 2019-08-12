package redstoneparadox.nicetohave.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.redstoneparadox.nicetohave.util.Config
import java.util.*
import java.util.function.Function

class GoldRiverOreFeature(function_1: Function<Dynamic<*>, DefaultFeatureConfig>) : Feature<DefaultFeatureConfig>(function_1) {

    val riverGoldPercent = Config.getWorldOption("river_gold_percent", Config.doubleType, 10.0)

    override fun generate(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, rand: Random, pos: BlockPos, config: DefaultFeatureConfig): Boolean {
        val chance = rand.nextInt(100) + 1
        val oreState = getGoldOre(pos, world)
        if (oreState != null && chance < riverGoldPercent) world.setBlockState(pos, oreState, 0)
        return true
    }

    private fun getGoldOre(pos: BlockPos, world: IWorld): BlockState? {
        val block = world.getBlockState(pos).block
        if (world.getFluidState(pos.up()).fluid == Fluids.WATER) {
            return when(block) {
                Blocks.DIRT -> net.redstoneparadox.nicetohave.block.Blocks.DIRT_GOLD_ORE.defaultState
                Blocks.GRAVEL -> net.redstoneparadox.nicetohave.block.Blocks.GRAVEL_GOLD_ORE.defaultState
                Blocks.SAND -> net.redstoneparadox.nicetohave.block.Blocks.SAND_GOLD_ORE.defaultState
                else -> null
            }
        }

        return null
    }
}