package net.redstoneparadox.nicetohave.entity.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType

class NopeStatusEffect(statusEffectType: StatusEffectType, int_1: Int) : StatusEffect(statusEffectType, int_1) {

    override fun canApplyUpdateEffect(int_1: Int, int_2: Int): Boolean {
        return false
    }

    override fun isInstant(): Boolean {
        return false
    }
}