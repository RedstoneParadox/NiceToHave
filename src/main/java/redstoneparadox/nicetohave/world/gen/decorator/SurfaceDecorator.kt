package redstoneparadox.nicetohave.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import java.util.*
import java.util.function.Function
import java.util.stream.Stream
import kotlin.collections.ArrayList

class SurfaceDecorator(function_1: Function<Dynamic<*>, out NopeDecoratorConfig>) : Decorator<NopeDecoratorConfig>(function_1) {

    override fun getPositions(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, rand: Random, config: NopeDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        val positions = ArrayList<BlockPos>()
        for (x in 0..15) {
            for (z in 0..15) {
                positions.add(world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, pos.add(x, 0, z)).down())
            }
        }
        return positions.stream()
    }
}