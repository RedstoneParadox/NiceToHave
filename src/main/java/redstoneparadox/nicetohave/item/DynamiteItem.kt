package redstoneparadox.nicetohave.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import redstoneparadox.nicetohave.entity.ThrownDynamiteEntity
import redstoneparadox.nicetohave.networking.Packets
import java.util.*

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class DynamiteItem() : Item(Item.Settings().group(ItemGroup.TOOLS)) {

    override fun use(world: World, playerEntity: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack : ItemStack = playerEntity.getStackInHand(hand)
        if (!playerEntity.isCreative)
        {
            stack.decrement(1)
        }

        world.playSound(null, playerEntity.x, playerEntity.y, playerEntity.z, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (Random().nextFloat() * 0.4f + 0.8f))
        if (!world.isClient) {
            val dynamite = ThrownDynamiteEntity(world, playerEntity)
            dynamite.setItem(stack)
            dynamite.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, -5.0f, 0.5f, 1.0f)
            world.spawnEntity(dynamite)
            Packets.dispatchToAllWatching(dynamite, ::EntityPositionS2CPacket)
        }

        return TypedActionResult(ActionResult.SUCCESS, stack)
    }

}