package redstoneparadox.nicetohave.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.network.Packet
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World
import net.minecraft.world.explosion.Explosion
import redstoneparadox.nicetohave.item.Items
import redstoneparadox.nicetohave.networking.Packets

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class ThrownDynamiteEntity : ThrownItemEntity {

    constructor(type : EntityType<ThrownDynamiteEntity>, world : World) : super(type, world)

    constructor(world: World, livingEntity: LivingEntity) : super(EntityTypes.THROWN_DYNAMITE, livingEntity, world)

    constructor(world: World, x : Double, y : Double, z : Double) : super(EntityTypes.THROWN_DYNAMITE, x, y, z, world)

    override fun getDefaultItem(): Item? {
        return Items.DYNAMITE
    }

    override fun onCollision(hitResult: HitResult) {
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, 3.toByte())
            world.createExplosion(this, x, y + (this.height / 16.0f).toDouble(), z, 1.85f, Explosion.DestructionType.BREAK)
            this.remove()
        }
    }

    override fun createSpawnPacket(): Packet<*> {
        return Packets.newSpawnPacket(this)
    }

}