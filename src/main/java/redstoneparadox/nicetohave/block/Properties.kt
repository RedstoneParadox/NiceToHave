package redstoneparadox.nicetohave.block

import net.minecraft.state.property.IntProperty

object Properties {
    @JvmField
    val FERTILIZER_COUNT: IntProperty = IntProperty.of("fertilizer_count", 0, 4)
}