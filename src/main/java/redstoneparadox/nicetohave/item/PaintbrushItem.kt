package redstoneparadox.nicetohave.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.*
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import redstoneparadox.nicetohave.util.PaintedPlacementContext
import redstoneparadox.nicetohave.util.getBlock

class PaintbrushItem(settings: Settings) : Item(settings) {

    init {
        addPropertyGetter(Identifier("color")) { stack, world, entity ->
            if (entity is PlayerEntity) {
                val mainItem = entity.mainHandStack.item
                val offhandItem = entity.offHandStack.item
                val dye = when {
                    mainItem is DyeItem -> mainItem
                    offhandItem is DyeItem -> offhandItem
                    else -> null
                }
                return@addPropertyGetter when (dye?.color) {
                    DyeColor.WHITE -> 0.01f
                    DyeColor.ORANGE -> 0.02f
                    DyeColor.MAGENTA -> 0.03f
                    DyeColor.LIGHT_BLUE -> 0.04f
                    DyeColor.YELLOW -> 0.05f
                    DyeColor.LIME -> 0.06f
                    DyeColor.PINK -> 0.07f
                    DyeColor.GRAY -> 0.08f
                    DyeColor.LIGHT_GRAY -> 0.09f
                    DyeColor.CYAN -> 0.1f
                    DyeColor.PURPLE -> 0.11f
                    DyeColor.BLUE -> 0.12f
                    DyeColor.BROWN -> 0.13f
                    DyeColor.GREEN -> 0.14f
                    DyeColor.RED -> 0.15f
                    DyeColor.BLACK -> 0.16f
                    null -> 0.0f
                }
            }
            return@addPropertyGetter 0.0f
        }
    }

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
        override fun getInvStack(slot: Int): ItemStack = ItemStack.EMPTY
        override fun markDirty() = Unit
        override fun clear() = Unit
        override fun setInvStack(slot: Int, stack: ItemStack?) = Unit
        override fun removeInvStack(slot: Int): ItemStack = ItemStack.EMPTY
        override fun canPlayerUseInv(player: PlayerEntity?): Boolean = false
        override fun getInvSize(): Int = 0
        override fun takeInvStack(slot: Int, amount: Int): ItemStack = ItemStack.EMPTY
        override fun isInvEmpty(): Boolean = true
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