package io.github.redstoneparadox.nicetohave.util

import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.math.BlockPos

class PaintedPlacementContext(private val context: ItemUsageContext): ItemPlacementContext(context) {
    override fun getBlockPos(): BlockPos {
        return context.blockPos
    }
}