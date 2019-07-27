package net.redstoneparadox.nicetohave.misc

import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.DispenserBehavior
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
    }

    fun register(item : Item, behavior : DispenserBehavior) {
        DispenserBlock.registerBehavior(item, behavior)
    }
}