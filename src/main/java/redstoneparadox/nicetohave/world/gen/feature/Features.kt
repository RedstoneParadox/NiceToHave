package redstoneparadox.nicetohave.world.gen.feature

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import java.util.function.Function

object Features {

    val GOLD_RIVER_ORE : Feature<DefaultFeatureConfig> = GoldRiverOreFeature (Function { DefaultFeatureConfig.deserialize(it) })

    fun registerFeatures() {
        register("gold_river_ore", GOLD_RIVER_ORE)
    }

    private fun <C : FeatureConfig, F : Feature<C>> register(id: String, feature: F) {
        Registry.register(Registry.FEATURE, "nicetohave:$id", feature)
    }
}