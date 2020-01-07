package redstoneparadox.nicetohave.tag

import net.fabricmc.fabric.api.tag.TagRegistry
import net.minecraft.block.Block
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

object NiceToHaveBlockTags {
    val CONCRETE: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
    val CONCRETE_POWDER: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
    val GLASS: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
    val GLASS_PANE: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
    val TERRACOTTA: Tag<Block> = TagRegistry.block(Identifier("nicetohave", "glass"))
}