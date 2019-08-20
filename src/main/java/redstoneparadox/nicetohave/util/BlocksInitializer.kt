package redstoneparadox.nicetohave.util

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.Blocks
import redstoneparadox.nicetohave.util.config.Config

abstract class BlocksInitializer {

    protected fun register(block : Block, id : String, respectConfig : Boolean = true): Block? {
        if (!respectConfig || Config.getBool("blocks.$id")) {
            Registry.register(Registry.BLOCK, "nicetohave:$id", block)
        }
        return null
    }

    protected fun register(block: Block, id: String, configOption: String? = null): Block? {
        if (Config.getBool(configOption ?: "blocks.$id")) {
            Registry.register(Registry.BLOCK, "nicetohave:$id", block)
        }
        return null
    }

    protected fun registerFlammableBlocks(blocks : Array<Block>, entry : FlammableBlockRegistry.Entry) {
        for (block in blocks) {
            registerFlammableBlock(block, entry)
        }
    }

    protected fun registerFlammableBlock(block: Block, entry : FlammableBlockRegistry.Entry) {
        FlammableBlockRegistry.getDefaultInstance().add(block, entry)
    }

    protected fun copySettings(block: Block): Block.Settings {
        return FabricBlockSettings.copy(block).build()
    }
}