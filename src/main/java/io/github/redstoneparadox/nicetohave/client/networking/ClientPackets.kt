package io.github.redstoneparadox.nicetohave.client.networking

import net.fabricmc.fabric.api.network.PacketContext
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl
import net.minecraft.client.MinecraftClient
import net.minecraft.network.Packet
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket
import net.minecraft.util.Identifier
import net.minecraft.network.PacketByteBuf
import net.minecraft.world.World
import java.io.IOException
import java.util.*
import java.util.function.Supplier

object ClientPackets {

    fun registerPackets() {
        ClientSidePacketRegistryImpl.INSTANCE.register(Identifier("nicetohave", "spawn_dynamite")) { packetContext, packetByteBuff ->
            spawnFrom(packetContext, packetByteBuff)
        }
    }

    private fun <T : Packet<*>> readFrom(bytes: PacketByteBuf, packet: Supplier<T>): Optional<T> {
        val deserializePacket: T
        try {
            deserializePacket = packet.get()
            deserializePacket.read(bytes)
        } catch (e: IOException) {
            return Optional.empty()
        }

        return Optional.of(deserializePacket)
    }

    private fun spawnFrom(ctx: PacketContext, bytes: PacketByteBuf) {
        readFrom(bytes, Supplier { EntitySpawnS2CPacket() }).ifPresent { pkt ->
            ctx.taskQueue.execute {
                val world = MinecraftClient.getInstance().world
                Optional.ofNullable(pkt.entityTypeId.create(world as World)).ifPresent { entity ->
                    entity.updateTrackedPosition(pkt.x, pkt.y, pkt.z)
                    entity.setVelocity(pkt.velocityX, pkt.velocityY, pkt.velocityZ)
                    entity.pitch = (pkt.pitch * 360).toFloat() / 256.0f
                    entity.yaw = (pkt.yaw * 360).toFloat() / 256.0f
                    entity.entityId = pkt.id
                    entity.uuid = pkt.uuid
                    world.addEntity(pkt.id, entity)
                }
            }
        }
    }

}