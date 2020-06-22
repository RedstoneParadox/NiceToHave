package io.github.redstoneparadox.nicetohave.networking

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.server.PlayerStream
import net.minecraft.entity.Entity
import net.minecraft.network.Packet
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.network.PacketByteBuf


/**
 * Created by RedstoneParadox on 5/24/2019.
 */
object Packets {

    fun newSpawnPacket(entity: Entity): Packet<*> {
        val bytes = PacketByteBuf(Unpooled.buffer())
        EntitySpawnS2CPacket(entity).write(bytes)
        return CustomPayloadS2CPacket(Identifier("nicetohave", "spawn_dynamite"), bytes)
    }

    fun <T : Entity> dispatchToAllWatching(entity: T, packet: Function1<T, Packet<*>>) {
        // todo shouldn't the api be handling ServerPlayerEntity casts..?
        PlayerStream.watching(entity)
                .map<ServerPlayerEntity> { ServerPlayerEntity::class.java.cast(it) }
                .map<ServerPlayNetworkHandler> { p -> p.networkHandler }
                .forEach { h -> h.sendPacket(packet.invoke(entity)) }
    }
}