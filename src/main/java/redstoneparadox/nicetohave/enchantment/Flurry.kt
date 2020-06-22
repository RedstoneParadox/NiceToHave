package redstoneparadox.nicetohave.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import redstoneparadox.nicetohave.config.Config
import java.util.*

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
class Flurry(rarity: Rarity, target: EnchantmentTarget, slots: Array<out EquipmentSlot>) : Enchantment(rarity, target, slots) {

    override fun getMaxLevel(): Int {
        return Config.Enchantments.flurryMaxLevels.toInt()
    }

    companion object {

        val MODIFIER : EntityAttributeModifier = EntityAttributeModifier(UUID.fromString("007249f5-1300-4893-b9c5-9a607bf66c15"), "", 10.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        fun getChance(level : Int): Double {
            return level.toDouble() * Config.Enchantments.flurryHasteChance
        }
    }
}