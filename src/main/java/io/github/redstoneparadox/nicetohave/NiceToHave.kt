package io.github.redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks
import io.github.redstoneparadox.nicetohave.command.Commands
import io.github.redstoneparadox.nicetohave.enchantment.Enchantments
import io.github.redstoneparadox.nicetohave.entity.EntityTypes
import io.github.redstoneparadox.nicetohave.entity.effect.StatusEffects
import io.github.redstoneparadox.nicetohave.item.NiceToHaveItems
import io.github.redstoneparadox.nicetohave.item.wrench.WrenchItem
import io.github.redstoneparadox.nicetohave.misc.DispenserBehaviors
import io.github.redstoneparadox.nicetohave.misc.Listeners
import io.github.redstoneparadox.nicetohave.potion.Potions
import io.github.redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import io.github.redstoneparadox.nicetohave.tag.NiceToHaveBlockTags

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object NiceToHave : ModInitializer {
    private val logger : Logger = LogManager.getFormatterLogger("NiceToHave")

    override fun onInitialize() {
        out("Initializing Nice to Have.")

        for (block in Registry.BLOCK.stream()) {
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, Registry.BLOCK.getId(block))
        }

        PaintbrushRecipe.SERIALIZER
        PaintbrushRecipe.TYPE

        NiceToHaveBlockTags.CONCRETE
        NiceToHaveBlockTags.CONCRETE_POWDER
        NiceToHaveBlockTags.GLASS
        NiceToHaveBlockTags.GLASS_PANE
        NiceToHaveBlockTags.TERRACOTTA
        NiceToHaveBlockTags.VANILLA_WOOL
        NiceToHaveBlockTags.MAP

        EntityTypes.registerEntityTypes()
        StatusEffects.registerEffects()
        Potions.registerPotions()
        NiceToHaveBlocks.initBlocks()
        NiceToHaveItems.initItems()
        Enchantments.registerEnchantments()
        DispenserBehaviors.registerBehaviors()
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