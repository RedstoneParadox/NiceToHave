package net.redstoneparadox.nicetohave.world.biome

import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.DefaultBiomeFeatures
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.ChanceRangeDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import net.redstoneparadox.nicetohave.world.gen.decorator.Decorators
import net.redstoneparadox.nicetohave.world.gen.feature.Features

class GoldRiverBiome : Biome {

    constructor(temperature : Float = 0.6f, precipitation: Precipitation = Precipitation.RAIN, frozen : Boolean = false) : super(Biome
            .Settings()
            .configureSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
            .precipitation(precipitation).category(Category.RIVER).depth(-0.5f)
            .scale(0.2f).temperature(temperature).downfall(0.7f).waterColor(4159204).waterFogColor(329011).parent(null as String?)
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

        addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, configureFeature(Features.GOLD_RIVER, FeatureConfig.DEFAULT, Decorators.SURFACE, DecoratorConfig.DEFAULT))

        if (!frozen) {
            addFeature(GenerationStep.Feature.VEGETAL_DECORATION, configureFeature(Feature.SEAGRASS, SeagrassFeatureConfig(48, 0.4), Decorator.TOP_SOLID_HEIGHTMAP, DecoratorConfig.DEFAULT))
        }

        DefaultBiomeFeatures.addFrozenTopLayer(this)
        addSpawn(EntityCategory.WATER_CREATURE, SpawnEntry(EntityType.SQUID, 2, 1, 4))
        addSpawn(EntityCategory.WATER_CREATURE, SpawnEntry(EntityType.SALMON, 5, 1, 5))
        addSpawn(EntityCategory.AMBIENT, SpawnEntry(EntityType.BAT, 10, 8, 8))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SPIDER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SKELETON, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.CREEPER, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.SLIME, 100, 4, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
        addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.WITCH, 5, 1, 1))
    }
}