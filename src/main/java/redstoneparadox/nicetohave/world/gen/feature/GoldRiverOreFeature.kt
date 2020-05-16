package redstoneparadox.nicetohave.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import redstoneparadox.nicetohave.block.NiceToHaveBlocks
import redstoneparadox.nicetohave.config.Config
import java.util.*
import java.util.function.Function

class GoldRiverOreFeature(function_1: Function<Dynamic<*>, DefaultFeatureConfig>) : Feature<DefaultFeatureConfig>(function_1) {

    val riverGoldPercent = Config.World.riverGoldPercent

    override fun generate(serverWorldAccess: ServerWorldAccess, accessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: DefaultFeatureConfig): Boolean {
        val chance = random.nextInt(100) + 1
        val oreState = getGoldOre(pos, serverWorldAccess)
        if (oreState != null && chance < riverGoldPercent) serverWorldAccess.setBlockState(pos, oreState, 0)
        return true
    }

    private fun getGoldOre(pos: BlockPos, world: WorldAccess): BlockState? {
        val block = world.getBlockState(pos).block
        if (world.getFluidState(pos.up()).fluid == Fluids.WATER) {
            return when(block) {
                Blocks.DIRT -> NiceToHaveBlocks.DIRT_GOLD_ORE.defaultState
                Blocks.GRAVEL -> NiceToHaveBlocks.GRAVEL_GOLD_ORE.defaultState
                Blocks.SAND -> NiceToHaveBlocks.SAND_GOLD_ORE.defaultState
                else -> null
            }
        }

        return null
    }
}