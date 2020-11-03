package io.github.redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.block.PoleBlock
import io.github.redstoneparadox.nicetohave.config.Config

abstract class BlocksInitializer {
    protected fun <T : Block> register(id: String, block: T): T {
        return Registry.register(Registry.BLOCK, "nicetohave:$id", block)
    }

    protected fun <T : Block> register(id: String, block: T, vararg conditions : Boolean): T? {
        for (condition in conditions) {
            if (!condition) return null
        }
        return register(id, block)
    }

    protected fun registerFlammableBlocks(blocks : Array<Block?>, entry : FlammableBlockRegistry.Entry) {
        for (block in blocks) {
            registerFlammableBlock(block, entry)
        }
    }

    private fun registerFlammableBlock(block: Block?, entry : FlammableBlockRegistry.Entry) {
        if (block != null) {
            FlammableBlockRegistry.getDefaultInstance().add(block, entry)
        }
    }
}