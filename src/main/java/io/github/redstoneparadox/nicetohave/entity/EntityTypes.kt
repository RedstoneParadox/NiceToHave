package io.github.redstoneparadox.nicetohave.entity

import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.config.Config
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object EntityTypes {

    val THROWN_DYNAMITE: EntityType<ThrownDynamiteEntity> = FabricEntityTypeBuilder.create<ThrownDynamiteEntity>(SpawnGroup.MISC) { entityType, world ->  ThrownDynamiteEntity(entityType, world)}.size(EntityDimensions.fixed(0.25f, 0.25f)).trackable(4, 20).build()

    fun registerEntityTypes() {
        if (Config.Items.dynamite) Registry.register(Registry.ENTITY_TYPE, "nicetohave:thrown_dynamite", THROWN_DYNAMITE)
    }

}