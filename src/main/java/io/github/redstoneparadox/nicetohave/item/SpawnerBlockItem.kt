package io.github.redstoneparadox.nicetohave.item

import net.minecraft.block.Blocks
import net.minecraft.block.entity.MobSpawnerBlockEntity
import net.minecraft.entity.EntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.ActionResult

class SpawnerBlockItem(val entityType: EntityType<*>): BlockItem(Blocks.SPAWNER, Settings().group(ItemGroup.MISC)) {
    override fun place(itemPlacementContext: ItemPlacementContext): ActionResult {
        val result = super.place(itemPlacementContext)
        val world =itemPlacementContext.world
        val blockEntity = world.getBlockEntity(itemPlacementContext.blockPos)

        if (blockEntity is MobSpawnerBlockEntity) {
            blockEntity.logic.setEntityId(entityType)
        }

        return result
    }
}