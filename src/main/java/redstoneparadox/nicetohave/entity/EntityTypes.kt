package redstoneparadox.nicetohave.entity

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.config.Config

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object EntityTypes {

    val THROWN_DYNAMITE: EntityType<ThrownDynamiteEntity> = FabricEntityTypeBuilder.create<ThrownDynamiteEntity>(EntityCategory.MISC) { entityType, world ->  ThrownDynamiteEntity(entityType, world)}.size(EntityDimensions.fixed(0.25f, 0.25f)).trackable(4, 20).build()

    fun registerEntityTypes() {
        if (Config.Items.dynamite) Registry.register(Registry.ENTITY_TYPE, "nicetohave:thrown_dynamite", THROWN_DYNAMITE)
    }

}