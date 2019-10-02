package redstoneparadox.nicetohave.commands

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.registry.CommandRegistry
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import redstoneparadox.nicetohave.util.config.Config

object Commands {

    fun initCommands() = CommandRegistry.INSTANCE.register(false) { dispatcher: CommandDispatcher<ServerCommandSource>? ->
        if (Config.Misc.respawnCommand) {
            val stuckNode = CommandManager
                    .literal("respawn")
                    .executes(StuckCommand())
                    .build()
            dispatcher?.root?.addChild(stuckNode)
        }
    }
}