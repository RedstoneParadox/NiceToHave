package net.redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.FabricLoader
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.enchantment.Enchantments
import net.redstoneparadox.nicetohave.entity.EntityTypes
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.item.wrench.WrenchItem
import net.redstoneparadox.nicetohave.misc.DispenserBehaviors
import net.redstoneparadox.nicetohave.misc.Listeners
import net.redstoneparadox.nicetohave.networking.Packets
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.redstoneparadox.nicetohave.world.biome.Biomes

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class NiceToHave : ModInitializer {

    override fun onInitialize() {
        out("It's Nice To Have you!")
        EntityTypes.registerEntityTypes()
        Blocks.registerBlocks()
        Items.registerItems()
        Packets.registerPackets()
        Enchantments.registerEnchantments()
        DispenserBehaviors.registerBehaviors()
        Biomes.registerBiomes()
        Listeners.registerListeners()
        WrenchItem.init()
    }

    companion object {
        private val logger : Logger = LogManager.getFormatterLogger("NiceToHave")

        fun out(msg : Any) {
            logger.info("[Nice to Have] $msg")
        }

        fun clientOut(msg : Any) {
            logger.info("[Nice to Have (client)] $msg")
        }
    }
}