package net.redstoneparadox.nicetohave.misc

import net.minecraft.block.*
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.client.network.packet.EntityPositionS2CPacket
import net.minecraft.entity.projectile.Projectile
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.SystemUtil
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Position
import net.minecraft.world.World
import net.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.networking.Packets
import net.redstoneparadox.nicetohave.util.Config

object DispenserBehaviors {

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

        if (Config.getMiscOption("dispenser_crop_planting", Config.boolType, true)) {
            register(net.minecraft.item.Items.WHEAT_SEEDS, PlantingDispenserBehavior(Blocks.FARMLAND, Blocks.WHEAT))
            register(net.minecraft.item.Items.BEETROOT_SEEDS, PlantingDispenserBehavior(Blocks.FARMLAND, Blocks.BEETROOTS))
            register(net.minecraft.item.Items.MELON_SEEDS, PlantingDispenserBehavior(Blocks.FARMLAND, Blocks.MELON_STEM))
            register(net.minecraft.item.Items.PUMPKIN_SEEDS, PlantingDispenserBehavior(Blocks.FARMLAND, Blocks.PUMPKIN_STEM))
            register(net.minecraft.item.Items.CHORUS_FLOWER, PlantingDispenserBehavior(Blocks.END_STONE, Blocks.CHORUS_FLOWER))
            register(net.minecraft.item.Items.SUGAR_CANE, PlantingDispenserBehavior(arrayOf(Blocks.SAND, Blocks.DIRT), Blocks.SUGAR_CANE))
            val bambooFarmBlocks = arrayOf(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.RED_SAND)
            register(net.minecraft.item.Items.BAMBOO, PlantingDispenserBehavior(bambooFarmBlocks, Blocks.BAMBOO_SAPLING))
            val saplingFarmBlocks = arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND)
            val saplingFarmBlocks2 = arrayOf(Blocks.DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK)
            register(net.minecraft.item.Items.OAK_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks, Blocks.OAK_SAPLING))
            register(net.minecraft.item.Items.BIRCH_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks, Blocks.BIRCH_SAPLING))
            register(net.minecraft.item.Items.JUNGLE_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks, Blocks.JUNGLE_SAPLING))
            register(net.minecraft.item.Items.SPRUCE_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks, Blocks.SPRUCE_SAPLING))
            register(net.minecraft.item.Items.ACACIA_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks2, Blocks.ACACIA_SAPLING))
            register(net.minecraft.item.Items.DARK_OAK_SAPLING, PlantingDispenserBehavior(saplingFarmBlocks2, Blocks.DARK_OAK_SAPLING))
        }

        if (Config.getMiscOption("dispenser_ladder_placement", Config.boolType, true)) {
            register(net.minecraft.item.Items.LADDER, object : ItemDispenserBehavior() {
                override fun dispenseSilently(pointer: BlockPointer?, itemStack: ItemStack?): ItemStack {
                    val direction: Direction = pointer!!.blockState.get(DispenserBlock.FACING)
                    val world = pointer.world

                    if (direction == Direction.DOWN || direction == Direction.UP) {
                        var nextPosition = pointer.blockPos.offset(direction)
                        var stackCount = 0
                        val ladderDirection = getLadderState(nextPosition, world)

                        while (world.getBlockState(nextPosition).block == Blocks.AIR && stackCount != itemStack!!.count && ladderDirection != null) {
                            val ladderState = nextLadderState(nextPosition, world, ladderDirection) ?: break

                            world.setBlockState(nextPosition, ladderState)
                            stackCount++
                            nextPosition = nextPosition.offset(direction)
                        }

                        if (stackCount > 0) {
                            itemStack!!.decrement(stackCount)
                            return itemStack
                        }
                    }

                    return super.dispenseSilently(pointer, itemStack)
                }

                fun getLadderState(position: BlockPos, world: World): Direction? {

                    for (direction in Direction.values()) {
                        if (direction == Direction.UP || direction == Direction.DOWN) {
                            continue
                        }

                        val ladderState = Blocks.LADDER.defaultState.with(LadderBlock.FACING, direction)

                        if (Blocks.LADDER.canPlaceAt(ladderState, world, position)) {
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
            })
            register(net.minecraft.item.Items.SCAFFOLDING, object : ItemDispenserBehavior() {
                override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
                    val direction = pointer.blockState.get(DispenserBlock.FACING)
                    val world = pointer.world

                    if (direction == Direction.UP) {
                        var nextPosition = pointer.blockPos.offset(direction)
                        var stackCount = 0

                        while (world.getBlockState(nextPosition).block == Blocks.AIR && stackCount != stack.count) {
                            world.setBlockState(nextPosition, Blocks.SCAFFOLDING.defaultState)
                            stackCount++
                            nextPosition = nextPosition.up()
                        }

                        if (stackCount > 0) {
                            stack.decrement(stackCount)
                            return stack
                        }
                    }

                    return super.dispenseSilently(pointer, stack)
                }
            })
        }
    }

    fun register(item : Item, behavior : DispenserBehavior) {
        DispenserBlock.registerBehavior(item, behavior)
    }

    class PlantingDispenserBehavior(private val farmlandBlocks : Array<Block>, private val plant : Block) : ItemDispenserBehavior() {

        constructor(farmland: Block, plant: Block) : this(arrayOf(farmland), plant)

        override fun dispenseSilently(pointer: BlockPointer?, itemStack: ItemStack?): ItemStack {
            val stack = itemStack!!
            val world = pointer!!.world
            val direction = pointer.blockState.get(DispenserBlock.FACING)
            val farmBlock = world.getBlockState(pointer.blockPos.offset(direction).down()).block

            for (block in farmlandBlocks) {
                if (farmBlock == block && world.getBlockState(pointer.blockPos.offset(direction)).block == Blocks.AIR) {
                    world.setBlockState(pointer.blockPos.offset(direction), plant.defaultState)
                    stack.decrement(1)
                    return stack
                }
            }

            return super.dispenseSilently(pointer, stack)
        }
    }
}