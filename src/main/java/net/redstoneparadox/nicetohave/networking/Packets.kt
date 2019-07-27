package net.redstoneparadox.nicetohave.networking

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.network.PacketContext
import net.fabricmc.fabric.api.server.PlayerStream
import net.fabricmc.fabric.impl.network.ClientSidePacketRegistryImpl
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.packet.CustomPayloadS2CPacket
import net.minecraft.client.network.packet.EntitySpawnS2CPacket
import net.minecraft.entity.Entity
import net.minecraft.network.Packet
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.PacketByteBuf
import java.io.IOException
import java.util.*
import java.util.function.Supplier


/**
 * Created by RedstoneParadox on 5/24/2019.
 */
object Packets {


    fun registerPackets() {

        ClientSidePacketRegistryImpl.INSTANCE.register(Identifier("nicetohave", "spawn_dynamite")) { packetContext, packetByteBuff ->
            spawnFrom(packetContext, packetByteBuff)
        }
    }

    fun newSpawnPacket(entity: Entity): Packet<*> {
        val bytes = PacketByteBuf(Unpooled.buffer())
        EntitySpawnS2CPacket(entity).write(bytes)
        return CustomPayloadS2CPacket(Identifier("nicetohave", "spawn_dynamite"), bytes)
    }

    fun <T : Packet<*>> readFrom(bytes: PacketByteBuf, packet: Supplier<T>): Optional<T> {
        val deserializedPacket: T
        try {
            deserializedPacket = packet.get()
            deserializedPacket.read(bytes)
        } catch (e: IOException) {
            return Optional.empty()
        }

        return Optional.of(deserializedPacket)
    }

    fun spawnFrom(ctx: PacketContext, bytes: PacketByteBuf) {
        Packets.readFrom(bytes, Supplier { EntitySpawnS2CPacket() }).ifPresent { pkt ->
            ctx.taskQueue.execute {
                val world = MinecraftClient.getInstance().world
                Optional.ofNullable(pkt.entityTypeId.create(world)).ifPresent { entity ->
                    entity.updateTrackedPosition(pkt.x, pkt.y, pkt.z)
                    entity.setVelocity(pkt.velocityX, pkt.velocityY, pkt.velocityz)
                    entity.pitch = (pkt.pitch * 360).toFloat() / 256.0f
                    entity.yaw = (pkt.yaw * 360).toFloat() / 256.0f
                    entity.entityId = pkt.id
                    entity.uuid = pkt.uuid
                    world.addEntity(pkt.id, entity)
                }
            }
        }
    }

    fun <T : Entity> dispatchToAllWatching(entity: T, packet: Function1<T, Packet<*>>) {
        // todo shouldnt the api be handling ServerPlayerEntity casts..?
        PlayerStream.watching(entity)
                .map<ServerPlayerEntity> { ServerPlayerEntity::class.java.cast(it) }
                .map<ServerPlayNetworkHandler> { p -> p.networkHandler }
                .forEach { h -> h.sendPacket(packet.invoke(entity)) }
    }
}