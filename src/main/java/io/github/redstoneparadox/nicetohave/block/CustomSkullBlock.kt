package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SkullBlock

class CustomSkullBlock(skullType: SkullType?): SkullBlock(skullType, FabricBlockSettings.copy(Blocks.SKELETON_SKULL)) {
    enum class Type: SkullType {
        SPIDER,
        CAVE_SPIDER,
        BLAZE,
        MAGMA_CUBE
    }
}