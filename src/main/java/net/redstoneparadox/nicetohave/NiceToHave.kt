package net.redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.minecraft.util.registry.Registry
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.entity.EntityTypes
import net.redstoneparadox.nicetohave.entity.effect.StatusEffects
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.item.wrench.WrenchItem
import net.redstoneparadox.nicetohave.misc.DispenserBehaviors
import net.redstoneparadox.nicetohave.misc.Listeners
import net.redstoneparadox.nicetohave.potion.Potions
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.redstoneparadox.nicetohave.world.biome.Biomes
import net.redstoneparadox.nicetohave.world.gen.decorator.Decorators
import net.redstoneparadox.nicetohave.world.gen.feature.Features

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class NiceToHave : ModInitializer {

    override fun onInitialize() {
        out("It's Nice To Have you!")

        for (block in Registry.BLOCK.stream()) {
            WrenchItem.blockToInteraction(block)
            DispenserBehaviors.blockToDispenserBehavior(block, Registry.BLOCK.getId(block))
        }

        Listeners.initListeners()
        EntityTypes.registerEntityTypes()
        StatusEffects.registerEffects()
        Potions.registerPotions()
        Blocks.registerBlocks()
        Items.registerItems()
        //Enchantments.registerEnchantments()
        DispenserBehaviors.registerBehaviors()
        Decorators.registerDecorators()
        Features.registerFeatures()
        Biomes.registerBiomes()
    }

    companion object {
        private val logger : Logger = LogManager.getFormatterLogger("NiceToHave")

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
}