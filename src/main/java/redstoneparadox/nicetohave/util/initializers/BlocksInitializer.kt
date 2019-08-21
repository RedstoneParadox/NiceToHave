package redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.Config

abstract class BlocksInitializer {

    protected fun <T : Block> register(block : T, id : String, respectConfig : Boolean = true): T? {
        if (!respectConfig || Config.getBool("blocks.$id")) {
            return Registry.register(Registry.BLOCK, "nicetohave:$id", block) as T?
        }
        return null
    }

    protected fun <T : Block> register(block: T, id: String, configOption: String? = null): T? {
        if (Config.getBool(configOption ?: "blocks.$id")) {
            return Registry.register(Registry.BLOCK, "nicetohave:$id", block) as T?
        }
        return null
    }

    protected fun registerPole(block: Block, prefix: String): PoleBlock? {
        return register(PoleBlock(block), "${prefix}_pole", "blocks.poles")
    }

    protected fun registerFlammableBlocks(blocks : Array<Block?>, entry : FlammableBlockRegistry.Entry) {
        for (block in blocks) {
            registerFlammableBlock(block, entry)
        }
    }

    protected fun registerFlammableBlock(block: Block?, entry : FlammableBlockRegistry.Entry) {
        if (block != null) {
            FlammableBlockRegistry.getDefaultInstance().add(block, entry)
        }
    }

    protected fun copySettings(block: Block): Block.Settings {
        return FabricBlockSettings.copy(block).build()
    }
}