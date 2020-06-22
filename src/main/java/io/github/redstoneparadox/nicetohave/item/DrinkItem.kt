package io.github.redstoneparadox.nicetohave.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.world.World

class DrinkItem(component: FoodComponent) : Item(Item.Settings().group(ItemGroup.FOOD).food(component)) {

    override fun finishUsing(itemStack: ItemStack, world: World, livingEntity: LivingEntity): ItemStack {
        val eatenStack = super.finishUsing(itemStack, world, livingEntity);
        if (livingEntity is PlayerEntity && !livingEntity.isCreative) {
            if (eatenStack.isEmpty) {
                return ItemStack(Items.GLASS_BOTTLE)
            }
            else {
                livingEntity.inventory.insertStack(ItemStack(Items.GLASS_BOTTLE))
            }
        }

        return eatenStack
    }
}