package redstoneparadox.nicetohave.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.world.World

class DrinkItem(settings: Settings) : Item(settings) {

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