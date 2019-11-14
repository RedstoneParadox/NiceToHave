package redstoneparadox.nicetohave.util.initializers

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.PoleBlock
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.datagen.BasicModelBuilder
import redstoneparadox.nicetohave.util.datagen.CraftingRecipeBuilder
import redstoneparadox.nicetohave.util.datagen.LootTableBuilder
import redstoneparadox.nicetohave.util.datagen.VariantBlockStateBuilder

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

    protected fun registerWoodPole(prefix: String, blockOf : Block, woodModID: String = "minecraft"): PoleBlock? {
        if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            CraftingRecipeBuilder.generatePoleRecipe(prefix, woodModID)
            LootTableBuilder.generatePoleDrop("${prefix}_pole", woodModID)
            BasicModelBuilder.createPoleBlockModel(prefix, woodModID)
            BasicModelBuilder.createPoleItemModel(prefix)
            VariantBlockStateBuilder.generatePoleBlockState(prefix)
        }

        return register("${prefix}_pole", PoleBlock(blockOf), OldConfig.Blocks.poles)
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