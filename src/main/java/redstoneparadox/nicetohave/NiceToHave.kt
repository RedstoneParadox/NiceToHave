package redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloadListener
import net.minecraft.resource.ResourceType
import net.minecraft.resource.SynchronousResourceReloadListener
import net.minecraft.util.Identifier
import net.minecraft.util.TagHelper
import net.minecraft.util.profiler.Profiler
import net.minecraft.util.registry.Registry
import redstoneparadox.nicetohave.block.Blocks
import redstoneparadox.nicetohave.entity.EntityTypes
import redstoneparadox.nicetohave.entity.effect.StatusEffects
import redstoneparadox.nicetohave.item.Items
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.misc.DispenserBehaviors
import redstoneparadox.nicetohave.misc.Listeners
import redstoneparadox.nicetohave.potion.Potions
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import redstoneparadox.nicetohave.misc.Fuels
import redstoneparadox.nicetohave.world.biome.Biomes
import redstoneparadox.nicetohave.world.gen.decorator.Decorators
import redstoneparadox.nicetohave.world.gen.feature.Features
import java.lang.Exception
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt

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
        Fuels.registerFuels()
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