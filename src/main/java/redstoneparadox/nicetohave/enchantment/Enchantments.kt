package redstoneparadox.nicetohave.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.util.config.OldConfig


/**
 * Created by RedstoneParadox on 5/25/2019.
 */
object Enchantments {

    var FLURRY : Flurry = Flurry(Enchantment.Weight.UNCOMMON, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND))

    fun registerEnchantments() {
        if (OldConfig.Enchantments.flurry) Registry.register(Registry.ENCHANTMENT, "nicetohave:flurry", FLURRY)
    }
}