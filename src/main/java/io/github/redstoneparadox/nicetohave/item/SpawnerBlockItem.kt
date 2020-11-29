package io.github.redstoneparadox.nicetohave.item

import io.github.redstoneparadox.nicetohave.util.id
import net.minecraft.block.Blocks
import net.minecraft.block.entity.MobSpawnerBlockEntity
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.*
import net.minecraft.text.*
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class SpawnerBlockItem: BlockItem(Blocks.SPAWNER, Settings().group(ItemGroup.MISC).rarity(Rarity.RARE)) {
    override fun place(context: ItemPlacementContext): ActionResult {
        val result = super.place(context)
        val world =context.world
        val blockEntity = world.getBlockEntity(context.blockPos)
        val tag = context.stack.orCreateTag
        val id = if (tag.contains("entity")) { tag.getString("entity").id() } else { DEFAULT_ID }

        if (blockEntity is MobSpawnerBlockEntity) {
            val entityType = Registry.ENTITY_TYPE[id]
            blockEntity.logic.setEntityId(entityType)
        }

        return result
    }

    override fun appendTooltip(itemStack: ItemStack, world: World?, list: MutableList<Text>, tooltipContext: TooltipContext) {
        super.appendTooltip(itemStack, world, list, tooltipContext)

        val tag = itemStack.orCreateTag
        val id = if (tag.contains("entity")) {
            tag.getString("entity")
        } else {
            DEFAULT_ID.toString()
        }.split(":")

        val text = TranslatableText("text.nicetohave.spawner_desc")
                .append(LiteralText(": "))
                .append(TranslatableText("entity.${id[0]}.${id[1]}"))
                .styled { it.withFormatting(Formatting.GRAY) }

        list.add(text)
    }

    companion object {
        val DEFAULT_ID: Identifier = "minecraft:pig".id()
    }
}
