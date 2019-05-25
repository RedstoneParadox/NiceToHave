package net.redstoneparadox.nicetohave.client

import net.fabricmc.api.ModInitializer
import net.redstoneparadox.nicetohave.client.render.entity.EntityRenderers

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
class NiceToHaveClient : ModInitializer {

    override fun onInitialize() {
        println("Greetings from the client!")
        EntityRenderers.registerRenderers()
    }

}