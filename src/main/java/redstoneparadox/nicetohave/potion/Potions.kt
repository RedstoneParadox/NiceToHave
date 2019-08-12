package redstoneparadox.nicetohave.potion

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.entity.effect.StatusEffects
import net.redstoneparadox.nicetohave.util.Config
import net.redstoneparadox.nicetohave.util.minutesToTicks
import net.redstoneparadox.nicetohave.util.secondsToTicks
import net.minecraft.entity.effect.StatusEffects as VanillaStatusEffects

object Potions {

    val INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(5, 30)))
    val LONG_INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(11, 0)))
    val STRONG_INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(2, 30), 2))
    //val NECTAR : Potion = Potion(StatusEffectInstance(VanillaStatusEffects.HEALTH_BOOST, minutesToTicks(6), 4), StatusEffectInstance(VanillaStatusEffects.RESISTANCE, minutesToTicks(6), 3), StatusEffectInstance(VanillaStatusEffects.FIRE_RESISTANCE, minutesToTicks(6)), StatusEffectInstance(VanillaStatusEffects.REGENERATION, secondsToTicks(30), 3))

    fun registerPotions() {
        register("insight", INSIGHT)
        register("long_insight", LONG_INSIGHT)
        register("strong_insight", STRONG_INSIGHT)
        //register("nectar", NECTAR)

        redstoneparadox.nicetohave.potion.PotionRecipes.registerRecipes()
    }

    private fun register(id: String, potion: Potion) {
        var configKey = id;

        if (configKey.startsWith("long_")) {
            configKey = configKey.removePrefix("long_")
        }
        else if (configKey.startsWith("strong_")) {
            configKey = configKey.removePrefix("strong_")
        }

        if (Config.getPotionOption(configKey, Config.boolType, true)) {
            Registry.register(Registry.POTION, "nicetohave:$id", potion)
        }
    }
}