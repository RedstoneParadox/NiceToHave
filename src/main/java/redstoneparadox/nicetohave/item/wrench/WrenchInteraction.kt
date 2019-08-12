package redstoneparadox.nicetohave.item.wrench

import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

typealias WrenchInteraction = (World, BlockState, BlockPos) -> BlockState

