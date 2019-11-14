package redstoneparadox.nicetohave.world.biome

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.RiverBiome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.feature.FeatureConfig
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.world.gen.decorator.Decorators
import redstoneparadox.nicetohave.world.gen.feature.Features
import net.minecraft.world.biome.Biomes as VanillaBiomes


object Biomes {

    val BAD_LANDS_RIVER = RiverBiome()
    
    fun registerBiomes() {
        if (OldConfig.World.goldInRivers) {
            BAD_LANDS_RIVER.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Biome.configureFeature(Features.GOLD_RIVER_ORE, FeatureConfig.DEFAULT, Decorators.SURFACE, DecoratorConfig.DEFAULT))
            register("badlands_river", BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.BADLANDS_PLATEAU, BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.BADLANDS, BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.ERODED_BADLANDS, BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.MODIFIED_BADLANDS_PLATEAU, BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.MODIFIED_WOODED_BADLANDS_PLATEAU, BAD_LANDS_RIVER)
            OverworldBiomes.setRiverBiome(VanillaBiomes.WOODED_BADLANDS_PLATEAU, BAD_LANDS_RIVER)

            VanillaBiomes.FROZEN_RIVER.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Biome.configureFeature(Features.GOLD_RIVER_ORE, FeatureConfig.DEFAULT, Decorators.SURFACE, DecoratorConfig.DEFAULT))
        }
    }

    private fun register(id : String, biome : Biome) {
        Registry.register(Registry.BIOME, "nicetohave:$id", biome)
    }
}