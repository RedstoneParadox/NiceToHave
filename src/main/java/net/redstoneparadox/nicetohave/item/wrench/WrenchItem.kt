package net.redstoneparadox.nicetohave.item.wrench

import net.minecraft.block.*
import net.minecraft.block.enums.BlockHalf
import net.minecraft.block.enums.SlabType
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.Direction
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.block.Blocks
import net.minecraft.block.Blocks as VanillaBlocks

class WrenchItem(settings: Settings?) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val blockState = context.world.getBlockState(context.blockPos)
        val interaction = interactions[blockState.block]
        if (interaction != null) {
           context.world.setBlockState(context.blockPos, interaction(context.world, blockState, context.blockPos))
            return ActionResult.SUCCESS
        }
        else {
            val rotatedState = blockState.block.rotate(blockState, BlockRotation.CLOCKWISE_90)
            if (rotatedState != blockState) {
                context.world.setBlockState(context.blockPos, rotatedState)
                return ActionResult.SUCCESS
            }
        }

        return ActionResult.PASS
    }

    companion object {
        private val interactions : HashMap<Block, WrenchInteraction> = HashMap()
        
        private val STAIR_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            when (blockState.get(StairsBlock.FACING)) {
                Direction.NORTH -> blockState.with(StairsBlock.FACING, Direction.SOUTH)
                Direction.SOUTH -> blockState.with(StairsBlock.FACING, Direction.WEST)
                Direction.WEST -> blockState.with(StairsBlock.FACING, Direction.EAST)
                Direction.EAST -> blockState.with(StairsBlock.FACING, Direction.NORTH).with(StairsBlock.HALF, nextBlockHalf(blockState))
                else -> throw Exception("Invalid BlockState $blockState")
            }
        }
        private val SLAB_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            when(blockState.get(SlabBlock.TYPE)) {
                SlabType.BOTTOM -> blockState.with(SlabBlock.TYPE, SlabType.TOP)
                SlabType.TOP -> blockState.with(SlabBlock.TYPE, SlabType.BOTTOM)
                SlabType.DOUBLE -> blockState
                else -> throw Exception("Invalid BlockState $blockState")
            }
        }
        private val TRAPDOOR_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            when(blockState.get(TrapdoorBlock.FACING)) {
                Direction.NORTH -> blockState.with(TrapdoorBlock.FACING, Direction.EAST)
                Direction.EAST -> blockState.with(TrapdoorBlock.FACING, Direction.SOUTH)
                Direction.SOUTH -> blockState.with(TrapdoorBlock.FACING, Direction.WEST)
                Direction.WEST -> blockState.with(TrapdoorBlock.FACING, Direction.NORTH).with(StairsBlock.HALF, nextBlockHalf(blockState))
                else -> throw Exception("Invalid BlockState ${blockState}")
            }
        }
        private val PILLAR_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            when(blockState.get(PillarBlock.AXIS)) {
                Direction.Axis.field_11048 -> blockState.with(PillarBlock.AXIS, Direction.Axis.field_11052)
                Direction.Axis.field_11052 -> blockState.with(PillarBlock.AXIS, Direction.Axis.field_11051)
                Direction.Axis.field_11051 -> blockState.with(PillarBlock.AXIS, Direction.Axis.field_11048)
                else -> throw Exception("Invalid BlockState $blockState")
            }
        }
        private val SIGN_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            val currentRot = blockState.get(SignBlock.ROTATION)

            if (currentRot < 15) {
                blockState.with(SignBlock.ROTATION, currentRot + 1)
            }
            else {
                blockState.with(SignBlock.ROTATION, 0)
            }
        }
        private val FACING_INTERACTION : WrenchInteraction = { world, blockState, blockPos ->
            when (blockState.get(DispenserBlock.FACING)) {
                Direction.UP -> blockState.with(DispenserBlock.FACING, Direction.NORTH)
                Direction.NORTH -> blockState.with(DispenserBlock.FACING, Direction.EAST)
                Direction.EAST -> blockState.with(DispenserBlock.FACING, Direction.SOUTH)
                Direction.SOUTH -> blockState.with(DispenserBlock.FACING, Direction.WEST)
                Direction.WEST -> blockState.with(DispenserBlock.FACING, Direction.DOWN)
                Direction.DOWN -> blockState.with(DispenserBlock.FACING, Direction.UP)
                else -> throw NullPointerException()
            }
        }
        
        private fun nextBlockHalf(state: BlockState): BlockHalf {
            return when(state.get(StairsBlock.HALF)) {
                BlockHalf.TOP -> BlockHalf.BOTTOM
                BlockHalf.BOTTOM -> BlockHalf.TOP
                else -> throw Exception("Invalid BlockState $state")
            }
        }

        fun registerInteraction(block : Block, interaction: WrenchInteraction) {
            interactions[block] = interaction
        }

        @Deprecated("Should not need this in the future.")
        private fun registerForEach(blocks : Array<Block>, interaction: WrenchInteraction) {
            for (block in blocks) {
                interactions[block] = interaction
            }
        }

        fun init() {
            for (block in Registry.BLOCK.stream()) {
                blockToInteraction(block)
            }

            registerForEach(arrayOf(VanillaBlocks.PISTON, VanillaBlocks.STICKY_PISTON)) { world, blockState, blockPos ->
                println(blockState)
                if (blockState.get(PistonBlock.EXTENDED) == true) {
                    return@registerForEach blockState
                }

                return@registerForEach when (blockState.get(PistonBlock.FACING)) {
                    Direction.UP -> blockState.with(PistonBlock.FACING, Direction.NORTH)
                    Direction.NORTH -> blockState.with(PistonBlock.FACING, Direction.EAST)
                    Direction.EAST -> blockState.with(PistonBlock.FACING, Direction.SOUTH)
                    Direction.SOUTH -> blockState.with(PistonBlock.FACING, Direction.WEST)
                    Direction.WEST -> blockState.with(PistonBlock.FACING, Direction.DOWN)
                    Direction.DOWN -> blockState.with(PistonBlock.FACING, Direction.UP)
                    else -> throw NullPointerException()
                }
            }
            registerForEach(arrayOf(VanillaBlocks.DISPENSER, VanillaBlocks.DROPPER)) { world, blockState, blockPos ->
                return@registerForEach when (blockState.get(DispenserBlock.FACING)) {
                    Direction.UP -> blockState.with(DispenserBlock.FACING, Direction.NORTH)
                    Direction.NORTH -> blockState.with(DispenserBlock.FACING, Direction.EAST)
                    Direction.EAST -> blockState.with(DispenserBlock.FACING, Direction.SOUTH)
                    Direction.SOUTH -> blockState.with(DispenserBlock.FACING, Direction.WEST)
                    Direction.WEST -> blockState.with(DispenserBlock.FACING, Direction.DOWN)
                    Direction.DOWN -> blockState.with(DispenserBlock.FACING, Direction.UP)
                    else -> throw NullPointerException()
                }
            }

        }
        
        fun blockToInteraction(block: Block) {
            when (block) {
                is PillarBlock -> registerInteraction(block, PILLAR_INTERACTION)
                is TrapdoorBlock -> registerInteraction(block, TRAPDOOR_INTERACTION)
                is SlabBlock -> registerInteraction(block, SLAB_INTERACTION)
                is StairsBlock -> registerInteraction(block, STAIR_INTERACTION)
                is SignBlock -> registerInteraction(block, SIGN_INTERACTION)
                is FacingBlock -> registerInteraction(block, FACING_INTERACTION)
            }
        }
    }
}