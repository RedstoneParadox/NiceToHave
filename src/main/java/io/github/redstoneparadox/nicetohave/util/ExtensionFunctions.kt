package io.github.redstoneparadox.nicetohave.util

import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Useful for situations in which you need to cast to a type with generic
 * arguments; this func gets around type-erasure.
 */
inline fun <reified T> Any.tryAs(): T? = this as? T

fun World.getBlock(pos: BlockPos): Block = this.getBlockState(pos).block

fun String.id(): Identifier {
    return Identifier(this)
}