package io.github.redstoneparadox.nicetohave.misc

import net.minecraft.block.*
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Position
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import io.github.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import io.github.redstoneparadox.nicetohave.item.NiceToHaveItems
import io.github.redstoneparadox.nicetohave.networking.Packets
import io.github.redstoneparadox.nicetohave.util.getBlock
import io.github.redstoneparadox.nicetohave.config.Config
import net.minecraft.item.Items as VanillaItems

object DispenserBehaviors {

    val saplingFarmBlocks: Array<Block> = arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND)
    val bambooFarmBlocks = arrayOf(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.RED_SAND)

    fun registerBehaviors() {
        if (Config.Items.dynamite && NiceToHaveItems.DYNAMITE != null) {
            register(NiceToHaveItems.DYNAMITE!!, object : ProjectileDispenserBehavior() {
                var entity: ThrownDynamiteEntity? = null

                override fun createProjectile(world: World, position: Position, itemStack: ItemStack): ProjectileEntity {
                    entity = ThrownDynamiteEntity(world, position.x, position.y, position.z)
                    entity!!.setItem(itemStack)
                    return entity as ProjectileEntity
                }

                override fun dispenseSilently(blockPointer_1: BlockPointer?, itemStack_1: ItemStack?): ItemStack {
                    val stack = super.dispenseSilently(blockPointer_1, itemStack_1)

                    if (entity != null) {
                        entity = null
                    }

                    return stack
                }
            })
        }
        if (Config.Redstone.dispenserCropPlanting) {
            register(VanillaItems.BAMBOO, PlantingDispenserBehavior(bambooFarmBlocks, Blocks.BAMBOO_SAPLING))
            register(VanillaItems.NETHER_WART, PlantingDispenserBehavior(arrayOf(Blocks.SOUL_SAND), Blocks.NETHER_WART))
            register(VanillaItems.BAMBOO, PlantingDispenserBehavior(bambooFarmBlocks, Blocks.BAMBOO_SAPLING))
            register(VanillaItems.KELP, PlantingDispenserBehavior(null, Blocks.KELP_PLANT, true))
        }
        if (Config.Items.fertilizer && NiceToHaveItems.FERTILIZER != null) {
            register(NiceToHaveItems.FERTILIZER!!, object : FallibleItemDispenserBehavior() {
                override fun dispenseSilently(blockPointer_1: BlockPointer, itemStack: ItemStack): ItemStack {
                    isSuccess = true
                    val world = blockPointer_1.world
                    val pos = blockPointer_1.pos.offset(blockPointer_1.blockState.get(DispenserBlock.FACING))
                    if (!BoneMealItem.useOnFertilizable(itemStack, world, pos) && !BoneMealItem.useOnGround(itemStack, world, pos, null as Direction?)) {
                        isSuccess = false
                    } else if (!world.isClient) {
                        world.syncGlobalEvent(2005, pos, 0)
                    }

                    return itemStack
                }
            })
        }
    }

    fun register(item : Item, behavior : DispenserBehavior) {
        DispenserBlock.registerBehavior(item, behavior)
    }

    fun blockToDispenserBehavior(block : Block, id : Identifier) {
        if (Config.Redstone.dispenserCropPlanting) {
            when (block) {
                is SaplingBlock -> register(Item.fromBlock(block), PlantingDispenserBehavior(saplingFarmBlocks, block))
                is CropBlock, is StemBlock -> {
                    val seed = Item.fromBlock(block)
                    if (seed != Blocks.AIR) {
                        register(seed, PlantingDispenserBehavior(arrayOf(Blocks.FARMLAND), block))
                    }
                }
            }
        }
        if (Config.Redstone.dispenserLadderPlacement) {
            when (block) {
                is LadderBlock -> register(Registry.ITEM.get(id), LadderBehavior(block))
                is ScaffoldingBlock -> register(Registry.ITEM.get(id), LadderBehavior(block, true))
            }
        }
    }

    class PlantingDispenserBehavior(private val farmlandBlocks : Array<Block>?, private val plant : Block, private val requiresWater : Boolean = false) : FallibleItemDispenserBehavior() {

        override fun dispenseSilently(pointer: BlockPointer, itemStack: ItemStack): ItemStack {
            isSuccess = false
            val world = pointer.world
            val direction = pointer.blockState.get(DispenserBlock.FACING)
            val farmBlock = world.getBlockState(pointer.pos.offset(direction).down(1)).block

            if (farmlandBlocks == null) {
                if (checkWaterReq(world, pointer.pos.offset(direction))) {
                    if (plant.canPlaceAt(world.getBlockState(pointer.pos.offset(direction).down(1)), world, pointer.pos.offset(direction))) {
                        world.setBlockState(pointer.pos.offset(direction), plant.defaultState)
                        itemStack.decrement(1)
                        isSuccess = true
                    }
                }
            }
            else {
                for (block in farmlandBlocks) {
                    val targetBlock = if (requiresWater) Blocks.WATER else Blocks.AIR
                    if (farmBlock == block && world.getBlockState(pointer.pos.offset(direction)).block == targetBlock) {
                        world.setBlockState(pointer.pos.offset(direction), plant.defaultState)
                        itemStack.decrement(1)
                        isSuccess = true
                    }
                }
            }

            return itemStack
        }

        private fun checkWaterReq(world: World, targetPos : BlockPos): Boolean {
            if (!requiresWater) return true
            else if (world.getBlock(targetPos) == Blocks.WATER) return true
            return false
        }
    }
    class LadderBehavior(val ladder : Block, private val upwardsOnly : Boolean = false) : FallibleItemDispenserBehavior() {
        override fun dispenseSilently(pointer: BlockPointer, itemStack: ItemStack): ItemStack {
            val direction: Direction = pointer.blockState.get(DispenserBlock.FACING)
            val world = pointer.world

            isSuccess = false

            if (upwardsOnly && direction == Direction.DOWN) {
                return super.dispenseSilently(pointer, itemStack)
            }

            if (direction == Direction.DOWN || direction == Direction.UP) {
                var nextPosition = pointer.pos.offset(direction)
                var stackCount = 0
                val ladderDirection = getLadderState(nextPosition, world)

                while (world.getBlockState(nextPosition).block == Blocks.AIR && stackCount != itemStack.count && ladderDirection != null) {
                    val ladderState = nextLadderState(nextPosition, world, ladderDirection) ?: break

                    world.setBlockState(nextPosition, ladderState)
                    stackCount++
                    nextPosition = nextPosition.offset(direction)
                }

                if (stackCount > 0) {
                    itemStack.decrement(stackCount)
                    isSuccess = true
                }
            }

            return itemStack
        }

        private fun getLadderState(position: BlockPos, world: World): Direction? {

            for (direction in Direction.values()) {
                if (direction == Direction.UP || direction == Direction.DOWN) {
                    continue
                }

                val ladderState = ladder.defaultState.with(LadderBlock.FACING, direction)

                if (ladder.canPlaceAt(ladderState, world, position)) {
                    return direction
                }
            }

            return null
        }

        fun nextLadderState(position: BlockPos, world: World, direction: Direction): BlockState? {
            val ladderState = Blocks.LADDER.defaultState.with(LadderBlock.FACING, direction)

            if (Blocks.LADDER.canPlaceAt(ladderState, world, position)) {
                return ladderState
            }

            return null
        }
    }
}