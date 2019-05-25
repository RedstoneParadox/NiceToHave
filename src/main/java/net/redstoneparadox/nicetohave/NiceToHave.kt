package net.redstoneparadox.nicetohave

import net.fabricmc.api.ModInitializer
import net.redstoneparadox.nicetohave.entity.EntityTypes
import net.redstoneparadox.nicetohave.item.Items
import net.redstoneparadox.nicetohave.networking.Packets

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
class NiceToHave : ModInitializer {

    override fun onInitialize() {
        println("Hello, world!")
        EntityTypes.registerEntityTypes()
        Items.registerItems()
        Packets.registerPackets()
    }
}