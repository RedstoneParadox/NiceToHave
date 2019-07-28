package net.redstoneparadox.nicetohave.misc

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.client.network.packet.EntityPositionS2CPacket
import net.minecraft.entity.projectile.Projectile
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.SystemUtil
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Position
import net.minecraft.world.World
import net.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.networking.Packets

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

    fun register(item : Item, behavior : DispenserBehavior) {
        DispenserBlock.registerBehavior(item, behavior)
    }

    class PlantingDispenserBehavior(val farmlandBlocks : Array<Block>, val plant : Block) : ItemDispenserBehavior() {

        constructor(farmland: Block, plant: Block) : this(arrayOf(farmland), plant)

        override fun dispenseSilently(pointer: BlockPointer?, itemStack: ItemStack?): ItemStack {
            var stack = itemStack!!
            var world = pointer!!.world
            var direction = pointer.blockState.get(DispenserBlock.FACING)
            var farmBlock = world.getBlockState(pointer.blockPos.offset(direction).down()).block

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