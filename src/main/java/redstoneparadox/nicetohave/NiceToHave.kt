package redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import redstoneparadox.nicetohave.command.Commands
import redstoneparadox.nicetohave.block.NiceToHaveBlocks
import redstoneparadox.nicetohave.enchantment.Enchantments
import redstoneparadox.nicetohave.entity.EntityTypes
import redstoneparadox.nicetohave.entity.effect.StatusEffects
import redstoneparadox.nicetohave.item.NiceToHaveItems
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.misc.DispenserBehaviors
import redstoneparadox.nicetohave.misc.Listeners
import redstoneparadox.nicetohave.potion.Potions
import redstoneparadox.nicetohave.recipe.DummyRecipe
import redstoneparadox.nicetohave.util.config.OldConfig
import redstoneparadox.nicetohave.util.datagen.*
import redstoneparadox.nicetohave.world.biome.Biomes
import redstoneparadox.nicetohave.world.gen.decorator.Decorators
import redstoneparadox.nicetohave.world.gen.feature.Features

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object NiceToHave : ModInitializer {

    private val logger : Logger = LogManager.getFormatterLogger("NiceToHave")

    init {
        OldConfig
        DummyRecipe.SERIALIZER
        DummyRecipe.TYPE
    }

    override fun onInitialize() {
        out("It's Nice To Have you!")

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
        NiceToHaveDatagen.generateData()
        Listeners.initListeners()
        Commands.initCommands()
    }

    fun out(msg : Any) {
        logger.info("[Nice to Have] $msg")
    }

    fun warn(msg: Any) {
        logger.warn("[Nice to Have] $msg")
    }

    fun error(msg: Any) {
        logger.error("[Nice to Have] $msg")
    }

    fun clientOut(msg : Any) {
        logger.info("[Nice to Have (client)] $msg")
    }

}