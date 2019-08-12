package redstoneparadox.nicetohave.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot


/**
 * Created by RedstoneParadox on 5/25/2019.
 */
object Enchantments {

    var FLURRY : Flurry = Flurry(Enchantment.Weight.UNCOMMON, EnchantmentTarget.field_9074, arrayOf(EquipmentSlot.MAINHAND))

    fun registerEnchantments() {
        //Registry.register(Registry.ENCHANTMENT, "nicetohave:flurry", FLURRY)
    }
}