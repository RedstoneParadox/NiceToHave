package net.redstoneparadox.nicetohave.item.wrench

import net.minecraft.block.*
import net.minecraft.block.enums.BlockHalf
import net.minecraft.block.enums.SlabType
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.math.Direction

class WrenchItem(settings: Settings?) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val blockState = context.world.getBlockState(context.blockPos)
        val interaction = interactions[blockState.block]
        if (interaction != null) {
           context.world.setBlockState(context.blockPos, interaction(context.world, blockState, context.blockPos))
        }
        else {
            val classInteraction = classInteractions[blockState.block.javaClass]

            if (classInteraction != null) {
                context.world.setBlockState(context.blockPos, classInteraction(context.world, blockState, context.blockPos))
            }
        }

        return super.useOnBlock(context)
    }

    companion object {
        private val interactions : HashMap<Block, WrenchInteraction> = HashMap()
        private val classInteractions : HashMap<Class<Block>, WrenchInteraction> = HashMap()

        fun registerInteraction(block : Block, interaction: WrenchInteraction) {
            interactions[block] = interaction
        }

        fun registerClassInteraction(blockClass : Class<Block>, interaction: WrenchInteraction) {
            classInteractions[blockClass] = interaction
        }

        fun registerForEach(blocks : Array<Block>, interaction: WrenchInteraction) {
            for (block in blocks) {
                interactions[block] = interaction
            }
        }

        fun init() {
            registerForEach(arrayOf(Blocks.PISTON, Blocks.STICKY_PISTON)) { world, blockState, blockPos ->
                println(blockState)
                if (blockState.get(PistonBlock.EXTENDED) == true) {
                    println("Hey!")
                    return@registerForEach blockState
                }

                return@registerForEach when (blockState.get(PistonBlock.FACING)) {
                    Direction.DOWN -> blockState.with(PistonBlock.FACING, Direction.UP)
                    Direction.UP -> blockState.with(PistonBlock.FACING, Direction.NORTH)
                    Direction.NORTH -> blockState.with(PistonBlock.FACING, Direction.SOUTH)
                    Direction.SOUTH -> blockState.with(PistonBlock.FACING, Direction.WEST)
                    Direction.WEST -> blockState.with(PistonBlock.FACING, Direction.EAST)
                    Direction.EAST -> blockState.with(PistonBlock.FACING, Direction.DOWN)
                    else -> throw NullPointerException()
                }
            }
            registerForEach(arrayOf(Blocks.DISPENSER, Blocks.DROPPER)) {world, blockState, blockPos ->
                return@registerForEach when (blockState.get(DispenserBlock.FACING)) {
                    Direction.DOWN -> blockState.with(PistonBlock.FACING, Direction.UP)
                    Direction.UP -> blockState.with(PistonBlock.FACING, Direction.NORTH)
                    Direction.NORTH -> blockState.with(PistonBlock.FACING, Direction.SOUTH)
                    Direction.SOUTH -> blockState.with(PistonBlock.FACING, Direction.WEST)
                    Direction.WEST -> blockState.with(PistonBlock.FACING, Direction.EAST)
                    Direction.EAST -> blockState.with(PistonBlock.FACING, Direction.DOWN)
                    else -> throw NullPointerException()
                }
            }
            registerClassInteraction(Blocks.ACACIA_STAIRS.javaClass) {world, blockState, blockPos ->

                val nextHalf = when(blockState.get(StairsBlock.HALF)) {
                    BlockHalf.TOP -> BlockHalf.BOTTOM
                    BlockHalf.BOTTOM -> BlockHalf.TOP
                    else -> throw Exception("Invalid BlockState ${blockState}")
                }

                return@registerClassInteraction when (blockState.get(StairsBlock.FACING)) {
                    Direction.NORTH -> blockState.with(StairsBlock.FACING, Direction.SOUTH)
                    Direction.SOUTH -> blockState.with(StairsBlock.FACING, Direction.WEST)
                    Direction.WEST -> blockState.with(StairsBlock.FACING, Direction.EAST)
                    Direction.EAST -> blockState.with(StairsBlock.FACING, Direction.NORTH).with(StairsBlock.HALF, nextHalf)
                    else -> throw Exception("Invalid BlockState ${blockState}")
                }
            }
            registerClassInteraction(Blocks.ACACIA_SLAB.javaClass) {world, blockState, blockPos ->
                return@registerClassInteraction when(blockState.get(SlabBlock.TYPE)) {
                    SlabType.BOTTOM -> blockState.with(SlabBlock.TYPE, SlabType.TOP)
                    SlabType.TOP -> blockState.with(SlabBlock.TYPE, SlabType.BOTTOM)
                    SlabType.DOUBLE -> blockState
                    else -> throw Exception("Invalid BlockState ${blockState}")
                }
            }
        }
    }
}