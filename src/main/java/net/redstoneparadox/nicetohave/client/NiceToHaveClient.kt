package net.redstoneparadox.nicetohave.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.redstoneparadox.nicetohave.NiceToHave
import net.redstoneparadox.nicetohave.client.render.entity.EntityRenderers

/**
 * Created by RedstoneParadox on 5/24/2019.
 */
class NiceToHaveClient : ClientModInitializer {

    override fun onInitializeClient() {
        NiceToHave.clientOut("It's Nice To Have you on the client!")
        EntityRenderers.registerRenderers()
    }

}