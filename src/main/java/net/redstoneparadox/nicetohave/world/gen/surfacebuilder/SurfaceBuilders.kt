package net.redstoneparadox.nicetohave.world.gen.surfacebuilder

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig

object SurfaceBuilders {

    val GOLD_RIVER = GoldRiverSurfaceBuilder(TernarySurfaceConfig::deserialize)

    fun registerSurfaceBuilders() {
        register(GOLD_RIVER, "gold_river")
    }

    fun <C : SurfaceConfig> register(builder : SurfaceBuilder<C>, id : String) {
        Registry.register(Registry.SURFACE_BUILDER, "nicetohave:$id", builder)
    }
}