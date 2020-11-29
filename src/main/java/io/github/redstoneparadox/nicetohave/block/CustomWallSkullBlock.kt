package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.block.SkullBlock
import net.minecraft.block.WallSkullBlock

class CustomWallSkullBlock(skullType: SkullBlock.SkullType) : WallSkullBlock(skullType, FabricBlockSettings.copy(Blocks.SKELETON_WALL_SKULL)) {
}