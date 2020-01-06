package redstoneparadox.nicetohave.item

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.DyeItem
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.tag.BlockTags
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
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

        if (player is PlayerEntity && BlockTags.WOOL.contains(block)) {
            val mainItem = player.mainHandStack.item
            val offhandItem = player.offHandStack.item
            if (mainItem is DyeItem) {
                val state = stateFromColor(mainItem.color)
                if (state.block != block) {
                    player.mainHandStack.decrement(1)
                    world.setBlockState(pos, state)
                    return ActionResult.SUCCESS
                }
            }
            else if (offhandItem is DyeItem) {
                val state = stateFromColor(offhandItem.color)
                world.setBlockState(pos, state)
                if (state.block != block) {
                    player.offHandStack.decrement(1)
                    world.setBlockState(pos, state)
                    return ActionResult.SUCCESS
                }
            }
        }
        return ActionResult.FAIL
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