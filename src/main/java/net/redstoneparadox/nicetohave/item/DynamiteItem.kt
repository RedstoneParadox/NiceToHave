package net.redstoneparadox.nicetohave.item

import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.ProjectileDispenserBehavior
import net.minecraft.client.network.packet.EntityPositionS2CPacket
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.Projectile
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.SystemUtil
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Position
import net.minecraft.world.World
import net.redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import net.redstoneparadox.nicetohave.networking.Packets

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class DynamiteItem(itemSettings: Settings?) : Item(itemSettings) {

    override fun use(world: World, playerEntity: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack : ItemStack = playerEntity.getStackInHand(hand)
        if (!playerEntity.isCreative)
        {
            stack.subtractAmount(1)
        }

        world.playSound(null, playerEntity.x, playerEntity.y, playerEntity.z, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (Item.random.nextFloat() * 0.4f + 0.8f))
        if (!world.isClient) {
            val dynamite = ThrownDynamiteEntity(world, playerEntity)
            dynamite.setItem(stack)
            dynamite.method_19207(playerEntity, playerEntity.pitch, playerEntity.yaw, -5.0f, 0.5f, 1.0f)
            world.spawnEntity(dynamite)
            Packets.dispatchToAllWatching(dynamite, ::EntityPositionS2CPacket)
        }

        return TypedActionResult(ActionResult.SUCCESS, stack)
    }

    companion object {
        fun registerDispenserBehavior() {
            DispenserBlock.registerBehavior(Items.DYNAMITE, object : ProjectileDispenserBehavior() {
                var entity : ThrownDynamiteEntity? = null

                override fun createProjectile(world: World, position: Position, itemStack: ItemStack): Projectile {
                    entity = SystemUtil.consume(ThrownDynamiteEntity(world, position.x, position.y, position.z), { it.setItem(itemStack) })
                    return entity as Projectile
                }

                override fun dispenseStack(blockPointer_1: BlockPointer?, itemStack_1: ItemStack?): ItemStack {
                    val stack = super.dispenseStack(blockPointer_1, itemStack_1)

                    if (entity != null)  {
                        Packets.dispatchToAllWatching(entity!!, ::EntityPositionS2CPacket)
                        entity = null
                    }

                    return stack
                }
            })
        }
    }
}