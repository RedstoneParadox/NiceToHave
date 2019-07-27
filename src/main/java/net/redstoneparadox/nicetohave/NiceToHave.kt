package net.redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.redstoneparadox.nicetohave.block.Blocks
import net.redstoneparadox.nicetohave.enchantment.Enchantments
import net.redstoneparadox.nicetohave.entity.EntityTypes
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.misc.DispenserBehaviors
import net.redstoneparadox.nicetohave.networking.Packets

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class NiceToHave : ModInitializer {

    override fun onInitialize() {
        println("Hello, world!")
        EntityTypes.registerEntityTypes()
        Blocks.registerBlocks()
        Items.registerItems()
        Packets.registerPackets()
        Enchantments.registerEnchantments()
        DispenserBehaviors.registerBehaviors()
    }
}