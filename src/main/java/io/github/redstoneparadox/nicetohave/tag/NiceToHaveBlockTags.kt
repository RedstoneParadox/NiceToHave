package io.github.redstoneparadox.nicetohave.tag

import net.fabricmc.fabric.api.tag.TagRegistry
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.fabricmc.fabric.impl.tag.extension.FabricTagHooks
import net.minecraft.block.Block
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

object NiceToHaveBlockTags {
    val CONCRETE: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "concrete"))
    val CONCRETE_POWDER: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "concrete_powder"))
    val GLASS: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
    val GLASS_PANE: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass_pane"))
    val TERRACOTTA: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "terracotta"))
    val VANILLA_CARPET: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "vanilla_carpet"))
    val VANILLA_WOOL: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "vanilla_wool"))
    internal val MAP: Map<Identifier, Tag<Block>> = mapOf(
            withID(CONCRETE),
            withID(CONCRETE_POWDER),
            withID(GLASS),
            withID(GLASS_PANE),
            withID(TERRACOTTA),
            withID(VANILLA_CARPET),
            withID(VANILLA_WOOL)
    )

    fun getBlockTag(id: Identifier): Tag<Block>? {
        return MAP[id]
    }

    private fun <T> withID(tag: Tag<T>): Pair<Identifier, Tag<T>> {
        val id = if (tag is Tag.Identified<T>) tag.id else Identifier("empty")
        return Pair(id, tag)
    }
}