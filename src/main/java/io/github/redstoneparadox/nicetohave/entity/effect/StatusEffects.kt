package io.github.redstoneparadox.nicetohave.entity.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.util.rgbToInt

object StatusEffects {

    var currentCount = 33

    val INSIGHT : StatusEffect = NopeStatusEffect(StatusEffectType.BENEFICIAL, rgbToInt(157u, 252u, 3u))

    fun registerEffects() {
        register("insight", INSIGHT)
    }

    private fun register(id: String, effect: StatusEffect) {
        Registry.register(Registry.STATUS_EFFECT, currentCount, "nicetohave:$id", effect)
        currentCount++
    }
}