package redstoneparadox.nicetohave.potion

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.entity.effect.StatusEffects
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.minutesToTicks

object Potions {

    val INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(5, 30)))
    val LONG_INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(11, 0)))
    val STRONG_INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(2, 30), 2))
    //val NECTAR : Potion = Potion(StatusEffectInstance(VanillaStatusEffects.HEALTH_BOOST, minutesToTicks(6), 4), StatusEffectInstance(VanillaStatusEffects.RESISTANCE, minutesToTicks(6), 3), StatusEffectInstance(VanillaStatusEffects.FIRE_RESISTANCE, minutesToTicks(6)), StatusEffectInstance(VanillaStatusEffects.REGENERATION, secondsToTicks(30), 3))

    fun registerPotions() {
        register("insight", INSIGHT, Config.Potions.insight)
        register("long_insight", LONG_INSIGHT, Config.Potions.insight)
        register("strong_insight", STRONG_INSIGHT, Config.Potions.insight)
        //register("nectar", NECTAR)

        PotionRecipes.registerRecipes()
    }

    private fun register(id: String, potion: Potion, condition : Boolean) {
        if (condition) {
            Registry.register(Registry.POTION, "nicetohave:$id", potion)
        }
    }
}