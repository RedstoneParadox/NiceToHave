package redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import redstoneparadox.nicetohave.block.NiceToHaveBlocks
import redstoneparadox.nicetohave.command.Commands
import redstoneparadox.nicetohave.enchantment.Enchantments
import redstoneparadox.nicetohave.entity.EntityTypes
import redstoneparadox.nicetohave.entity.effect.StatusEffects
import redstoneparadox.nicetohave.item.NiceToHaveItems
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.misc.DispenserBehaviors
import redstoneparadox.nicetohave.misc.Listeners
import redstoneparadox.nicetohave.potion.Potions
import redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import redstoneparadox.nicetohave.tag.NiceToHaveBlockTags
import redstoneparadox.nicetohave.world.biome.Biomes
import redstoneparadox.nicetohave.world.gen.decorator.Decorators
import redstoneparadox.nicetohave.world.gen.feature.Features

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object NiceToHave : ModInitializer {

    private val logger : Logger = LogManager.getFormatterLogger("NiceToHave")

    init {
        PaintbrushRecipe.SERIALIZER
        PaintbrushRecipe.TYPE

        NiceToHaveBlockTags.CONCRETE
        NiceToHaveBlockTags.CONCRETE_POWDER
        NiceToHaveBlockTags.GLASS
        NiceToHaveBlockTags.GLASS_PANE
        NiceToHaveBlockTags.TERRACOTTA
        NiceToHaveBlockTags.VANILLA_WOOL
    }

    override fun onInitialize() {
        out("Initializing Nice to Have.")

        for (block in Registry.BLOCK.stream()) {
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, Registry.BLOCK.getId(block))
        }

        EntityTypes.registerEntityTypes()
        StatusEffects.registerEffects()
        Potions.registerPotions()
        NiceToHaveBlocks.initBlocks()
        NiceToHaveItems.initItems()
        Enchantments.registerEnchantments()
        DispenserBehaviors.registerBehaviors()
        Decorators.registerDecorators()
        Features.registerFeatures()
        Biomes.registerBiomes()
        Listeners.initListeners()
        Commands.initCommands()
    }

    fun out(msg : Any) {
        logger.info("$msg")
    }

    fun warn(msg: Any) {
        logger.warn("$msg")
    }

    fun error(msg: Any) {
        logger.error("$msg")
    }

    fun clientOut(msg: Any) {
        logger.info("$msg")
    }

}