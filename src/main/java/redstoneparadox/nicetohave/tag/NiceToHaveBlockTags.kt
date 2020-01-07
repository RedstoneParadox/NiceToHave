package redstoneparadox.nicetohave.tag

import net.fabricmc.fabric.api.tag.FabricItemTags
import net.fabricmc.fabric.api.tag.FabricTag
import net.fabricmc.fabric.api.tag.FabricTagBuilder
import net.fabricmc.fabric.api.tag.TagRegistry
import net.minecraft.util.Identifier

object NiceToHaveBlockTags {
    val GLASS = TagRegistry.block(Identifier("nicetohave", "glass"))
}