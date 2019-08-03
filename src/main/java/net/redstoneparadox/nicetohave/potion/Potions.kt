package net.redstoneparadox.nicetohave.potion

import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.entity.effect.StatusEffects
import net.redstoneparadox.nicetohave.util.minutesToTicks

object Potions {

    val INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(5, 30)))
    val LONG_INSIGHT : Potion = Potion(StatusEffectInstance(StatusEffects.INSIGHT, minutesToTicks(11, 0)))

    fun registerPotions() {
        register("insight", INSIGHT)
        register("long_insight", LONG_INSIGHT)
    }

    private fun register(id: String, potion: Potion) {
        Registry.register(Registry.POTION, "nicetohave:$id", potion)
    }
}