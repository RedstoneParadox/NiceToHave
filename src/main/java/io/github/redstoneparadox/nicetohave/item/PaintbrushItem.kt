package io.github.redstoneparadox.nicetohave.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.*
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import io.github.redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import io.github.redstoneparadox.nicetohave.util.PaintedPlacementContext
import io.github.redstoneparadox.nicetohave.util.getBlock

class PaintbrushItem(settings: Settings): Item(settings) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val pos = context.blockPos
        val player = context.player
        val block = world.getBlock(pos)

        val recipe = world.recipeManager.getFirstMatch(PaintbrushRecipe.TYPE, PaintbrushInventory(block), world)
        if (recipe.isPresent && player is PlayerEntity) {

            fun paint(stack: ItemStack, color: DyeColor): ActionResult {
                val painted = recipe.get().craft(color)
                if (painted != null && painted != block) {
                    val state = painted.getPlacementState(PaintedPlacementContext(context))
                    world.setBlockState(pos, state)
                    if (!player.isCreative) stack.decrement(1)
                    return ActionResult.SUCCESS
                }
                return ActionResult.FAIL
            }

            val mainStack = player.mainHandStack
            val offhandStack = player.offHandStack
            if (mainStack.item is DyeItem) return paint(mainStack, (mainStack.item as DyeItem).color)
            else if (offhandStack.item is DyeItem) return paint(offhandStack, (offhandStack.item as DyeItem).color)
        }
        return ActionResult.FAIL
    }

    class PaintbrushInventory(val block: Block): Inventory {
        override fun getStack(slot: Int): ItemStack = ItemStack.EMPTY
        override fun markDirty() = Unit
        override fun clear() = Unit
        override fun setStack(slot: Int, stack: ItemStack?) = Unit
        override fun removeStack(slot: Int): ItemStack = ItemStack.EMPTY
        override fun canPlayerUse(player: PlayerEntity?): Boolean = false
        override fun size(): Int = 0
        override fun removeStack(slot: Int, amount: Int): ItemStack = ItemStack.EMPTY
        override fun isEmpty(): Boolean = true
    }

    private fun stateFromColor(color: DyeColor): BlockState = when (color) {
        DyeColor.WHITE -> Blocks.WHITE_WOOL.defaultState
        DyeColor.ORANGE -> Blocks.ORANGE_WOOL.defaultState
        DyeColor.MAGENTA -> Blocks.MAGENTA_WOOL.defaultState
        DyeColor.LIGHT_BLUE -> Blocks.LIGHT_BLUE_WOOL.defaultState
        DyeColor.YELLOW -> Blocks.YELLOW_WOOL.defaultState
        DyeColor.LIME -> Blocks.LIME_WOOL.defaultState
        DyeColor.PINK -> Blocks.PINK_WOOL.defaultState
        DyeColor.GRAY -> Blocks.GRAY_WOOL.defaultState
        DyeColor.LIGHT_GRAY -> Blocks.LIGHT_GRAY_WOOL.defaultState
        DyeColor.CYAN -> Blocks.CYAN_WOOL.defaultState
        DyeColor.PURPLE -> Blocks.PURPLE_WOOL.defaultState
        DyeColor.BLUE -> Blocks.BLUE_WOOL.defaultState
        DyeColor.BROWN -> Blocks.BROWN_WOOL.defaultState
        DyeColor.GREEN -> Blocks.GREEN_WOOL.defaultState
        DyeColor.RED -> Blocks.RED_WOOL.defaultState
        DyeColor.BLACK -> Blocks.BLACK_WOOL.defaultState
    }
}