package net.redstoneparadox.nicetohave.world.biome

import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.MineshaftFeature
import net.minecraft.world.gen.feature.MineshaftFeatureConfig
import net.minecraft.world.gen.feature.SeagrassFeatureConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig


class BadlandsRiverBiome : Biome {

    constructor() : super(Biome
            .Settings()
            .configureSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
            .precipitation(Precipitation.RAIN).category(Category.RIVER).depth(-0.5f)
            .scale(0.2f).temperature(0.6f).downfall(0.7f).waterColor(4159204).waterFogColor(329011).parent(null as String?)
    ) {
        addStructureFeature(Feature.MINESHAFT, MineshaftFeatureConfig(0.004, MineshaftFeature.Type.NORMAL))
        DefaultBiomeFeatures.addLandCarvers(this)
        DefaultBiomeFeatures.addDefaultStructures(this)
        DefaultBiomeFeatures.addDefaultLakes(this)
        DefaultBiomeFeatures.addDungeons(this)
        DefaultBiomeFeatures.addMineables(this)
        DefaultBiomeFeatures.addDefaultOres(this)
        DefaultBiomeFeatures.addDefaultDisks(this)
        DefaultBiomeFeatures.addWaterBiomeOakTrees(this)
        DefaultBiomeFeatures.addDefaultFlowers(this)
        DefaultBiomeFeatures.addDefaultGrass(this)
        DefaultBiomeFeatures.addDefaultMushrooms(this)
        DefaultBiomeFeatures.addDefaultVegetation(this)
        DefaultBiomeFeatures.addSprings(this)
        addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.SEAGRASS, SeagrassFeatureConfig(48, 0.4), Decorator.TOP_SOLID_HEIGHTMAP, DecoratorConfig.DEFAULT))
        DefaultBiomeFeatures.addFrozenTopLayer(this)
        addSpawn(EntityCategory.WATER_CREATURE, Biome.SpawnEntry(EntityType.SQUID, 2, 1, 4))
        addSpawn(EntityCategory.WATER_CREATURE, Biome.SpawnEntry(EntityType.SALMON, 5, 1, 5))
        addSpawn(EntityCategory.AMBIENT, Biome.SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.DROWNED, 100, 1, 1))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1))
    }
}