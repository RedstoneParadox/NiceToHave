package net.redstoneparadox.nicetohave.world.gen.feature

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import java.util.function.Function

object Features {

    val GOLD_RIVER : Feature<DefaultFeatureConfig> = GoldRiverFeature (Function { DefaultFeatureConfig.deserialize(it) })

    fun registerFeatures() {
        register("gold_river", GOLD_RIVER)
    }

    private fun <C : FeatureConfig, F : Feature<C>> register(id: String, feature: F) {
        Registry.register(Registry.FEATURE, "nicetohave:$id", feature)
    }
}