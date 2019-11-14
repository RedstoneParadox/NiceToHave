package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.minecraft.block.*
import net.minecraft.block.Blocks
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.initializers.BlocksInitializer

object NiceToHaveBlocks : BlocksInitializer() {

    val GOLD_BUTTON : Block? = register("gold_button", CustomButtonBlock(1), OldConfig.Redstone.goldButton)
    val ANALOG_REDSTONE_EMITTER : Block? = register("analog_redstone_emitter", AnalogRedstoneEmitterBlock(), OldConfig.Redstone.analogRedstoneEmitter)
    val CHAIN_LINK_FENCE : ChainLinkFenceBlock? = register("chain_link_fence", ChainLinkFenceBlock(), false)
    val TRIMMED_VINE : Block? = register("trimmed_vine", TrimmedVineBlock(), OldConfig.Blocks.trimmedVines)

    //Ore Blocks
    val DIRT_GOLD_ORE : Block = register("dirt_gold_ore", Block(copySettings(Blocks.DIRT)))
    val SAND_GOLD_ORE : Block = register("sand_gold_ore", SandBlock(14406560, copySettings(Blocks.SAND)))
    val GRAVEL_GOLD_ORE : Block = register("gravel_gold_ore", GravelBlock(copySettings(Blocks.GRAVEL)))

    //Poles
    val OAK_POLE : PoleBlock? = registerWoodPole("oak", Blocks.OAK_WOOD)
    val SPRUCE_POLE : PoleBlock? = registerWoodPole("spruce", Blocks.SPRUCE_WOOD)
    val BIRCH_POLE : PoleBlock? = registerWoodPole("birch", Blocks.BIRCH_WOOD)
    val JUNGLE_POLE : PoleBlock? = registerWoodPole("jungle", Blocks.JUNGLE_WOOD)
    val ACACIA_POLE : PoleBlock? = registerWoodPole("acacia", Blocks.ACACIA_WOOD)
    val DARK_OAK_POLE : PoleBlock? = registerWoodPole("dark_oak", Blocks.DARK_OAK_WOOD)

    val STRIPPED_OAK_POLE : PoleBlock? = registerWoodPole("stripped_oak", Blocks.OAK_WOOD)
    val STRIPPED_SPRUCE_POLE : PoleBlock? = registerWoodPole("stripped_spruce", Blocks.SPRUCE_WOOD)
    val STRIPPED_BIRCH_POLE : PoleBlock? = registerWoodPole("stripped_birch", Blocks.BIRCH_WOOD)
    val STRIPPED_JUNGLE_POLE : PoleBlock? = registerWoodPole("stripped_jungle", Blocks.JUNGLE_WOOD)
    val STRIPPED_ACACIA_POLE : PoleBlock? = registerWoodPole("stripped_acacia", Blocks.ACACIA_WOOD)
    val STRIPPED_DARK_OAK_POLE : PoleBlock? = registerWoodPole("stripped_dark_oak", Blocks.DARK_OAK_WOOD)

    fun initBlocks() {
        if (OldConfig.Blocks.poles) {
            registerFlammableBlocks(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
            registerFlammableBlocks(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), FlammableBlockRegistry.Entry(5, 20))
        }
    }

}