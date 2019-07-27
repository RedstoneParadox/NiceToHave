package net.redstoneparadox.nicetohave.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import java.util.*

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
class Flurry(weight: Weight, target: EnchantmentTarget, slots: Array<out EquipmentSlot>) : Enchantment(weight, target, slots) {

    override fun getMaximumLevel(): Int {
        return 4
    }

    companion object {

        val MODIFIER : EntityAttributeModifier = EntityAttributeModifier(UUID.fromString("007249f5-1300-4893-b9c5-9a607bf66c15"), "", 10.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        fun getChance(level : Int): Int {
            return level * 15
        }
    }
}