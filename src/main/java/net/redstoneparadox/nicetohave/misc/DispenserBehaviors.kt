package net.redstoneparadox.nicetohave.misc

import net.minecraft.block.*
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.client.network.packet.EntityPositionS2CPacket
import net.minecraft.entity.projectile.Projectile
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.SystemUtil
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Position
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import net.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.networking.Packets
import net.redstoneparadox.nicetohave.util.Config
import net.minecraft.item.Items as VanillaItems

object DispenserBehaviors {

    val saplingFarmBlocks: Array<Block> = arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND)
    val bambooFarmBlocks = arrayOf(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.RED_SAND)

    fun registerBehaviors() {
        register(Items.DYNAMITE, object : ProjectileDispenserBehavior() {
            var entity : ThrownDynamiteEntity? = null

            override fun createProjectile(world: World, position: Position, itemStack: ItemStack): Projectile {
                entity = SystemUtil.consume(ThrownDynamiteEntity(world, position.x, position.y, position.z), { it.setItem(itemStack) })
                return entity as Projectile
            }

            override fun dispenseSilently(blockPointer_1: BlockPointer?, itemStack_1: ItemStack?): ItemStack {
                val stack = super.dispenseSilently(blockPointer_1, itemStack_1)

                if (entity != null)  {
                    Packets.dispatchToAllWatching(entity!!, ::EntityPositionS2CPacket)
                    entity = null
                }

                return stack
            }
        })
        register(VanillaItems.BAMBOO, PlantingDispenserBehavior(bambooFarmBlocks, Blocks.BAMBOO_SAPLING))
        register(Items.FERTILIZER, object : FallibleItemDispenserBehavior() {
            override fun dispenseSilently(blockPointer_1: BlockPointer, itemStack: ItemStack): ItemStack {
                this.success = true
                val world = blockPointer_1.world
                val blockPos = blockPointer_1.blockPos.offset(blockPointer_1.blockState.get(DispenserBlock.FACING))
                if (!BoneMealItem.useOnFertilizable(itemStack, world, blockPos) && !BoneMealItem.useOnGround(itemStack, world, blockPos, null as Direction?)) {
                    this.success = false
                } else if (!world.isClient) {
                    world.playLevelEvent(2005, blockPos, 0)
                }

                return itemStack
            }
        })
        if (Config.getMiscOption("dispenser_crop_planting", Config.boolType, true)) {
            register(VanillaItems.NETHER_WART, PlantingDispenserBehavior(arrayOf(Blocks.SOUL_SAND), Blocks.NETHER_WART))
            register(VanillaItems.KELP, PlantingDispenserBehavior())
        }
    }

    fun register(item : Item, behavior : DispenserBehavior) {
        DispenserBlock.registerBehavior(item, behavior)
    }

    fun blockToDispenserBehavior(block : Block, id : Identifier) {
        if (Config.getMiscOption("dispenser_crop_planting", Config.boolType, true)) {
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
        if (Config.getMiscOption("dispenser_ladder_placement", Config.boolType, true)) {
            when (block) {
                is LadderBlock -> register(Registry.ITEM.get(id), LadderBehavior(block))
                is ScaffoldingBlock -> register(Registry.ITEM.get(id), LadderBehavior(block, true))
            }
        }
    }

    class PlantingDispenserBehavior(private val farmlandBlocks : Array<Block>, private val plant : Block) : FallibleItemDispenserBehavior() {

        override fun dispenseSilently(pointer: BlockPointer, itemStack: ItemStack): ItemStack {
            success = false
            val world = pointer.world
            val direction = pointer.blockState.get(DispenserBlock.FACING)
            val farmBlock = world.getBlockState(pointer.blockPos.offset(direction).down()).block

            for (block in farmlandBlocks) {
                if (farmBlock == block && world.getBlockState(pointer.blockPos.offset(direction)).block == Blocks.AIR) {
                    world.setBlockState(pointer.blockPos.offset(direction), plant.defaultState)
                    itemStack.decrement(1)
                    success = true
                }
            }

            return itemStack
        }
    }
    class LadderBehavior(val ladder : Block, private val upwardsOnly : Boolean = false) : FallibleItemDispenserBehavior() {
        override fun dispenseSilently(pointer: BlockPointer, itemStack: ItemStack): ItemStack {
            val direction: Direction = pointer.blockState.get(DispenserBlock.FACING)
            val world = pointer.world

            success = false

            if (upwardsOnly && direction == Direction.DOWN) {
                return super.dispenseSilently(pointer, itemStack)
            }

            if (direction == Direction.DOWN || direction == Direction.UP) {
                var nextPosition = pointer.blockPos.offset(direction)
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
                    success = true
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