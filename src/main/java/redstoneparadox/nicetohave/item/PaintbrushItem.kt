package redstoneparadox.nicetohave.item

import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.nbt.ByteTag
import net.minecraft.tag.BlockTags
import net.minecraft.tag.ItemTags
import net.minecraft.tag.Tag
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import redstoneparadox.nicetohave.util.getBlock
import java.lang.NullPointerException

class PaintbrushItem(settings: Settings) : Item(settings) {

    init {
        addPropertyGetter(Identifier("color")) { stack, world, entity ->
            val tag = stack.getOrCreateTag()["color"]
            if (tag is ByteTag) {
                val id = tag.int - 1
                if (id >= 0) return@addPropertyGetter when (DyeColor.byId(id)) {
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
                    else -> 0.0f
                }
            }
            return@addPropertyGetter 0.0f
        }
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world
        val pos = context.blockPos
        val block = world.getBlock(pos)
        val tag = context.stack.getOrCreateTag()["color"]

        if (tag is ByteTag && BlockTags.WOOL.contains(block)) {
            val id = tag.int - 1
            if (id >= 0) {
                val newBlock = when (val color = DyeColor.byId(id)) {
                    DyeColor.WHITE -> Blocks.WHITE_WOOL
                    DyeColor.ORANGE -> Blocks.ORANGE_WOOL
                    DyeColor.MAGENTA -> Blocks.MAGENTA_WOOL
                    DyeColor.LIGHT_BLUE -> Blocks.LIGHT_BLUE_WOOL
                    DyeColor.YELLOW -> Blocks.YELLOW_WOOL
                    DyeColor.LIME -> Blocks.LIME_WOOL
                    DyeColor.PINK -> Blocks.PINK_WOOL
                    DyeColor.GRAY -> Blocks.GRAY_WOOL
                    DyeColor.LIGHT_GRAY -> Blocks.LIGHT_GRAY_WOOL
                    DyeColor.CYAN -> Blocks.CYAN_WOOL
                    DyeColor.PURPLE -> Blocks.PURPLE_WOOL
                    DyeColor.BLUE -> Blocks.BLUE_WOOL
                    DyeColor.BROWN -> Blocks.BROWN_WOOL
                    DyeColor.GREEN -> Blocks.GREEN_WOOL
                    DyeColor.RED -> Blocks.RED_WOOL
                    DyeColor.BLACK -> Blocks.BLACK_WOOL
                    else -> block
                }
                if (newBlock != block) {
                    world.setBlockState(pos, newBlock.defaultState)
                    return ActionResult.SUCCESS
                }
            }
        }

        return ActionResult.FAIL
    }
}