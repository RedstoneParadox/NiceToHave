package redstoneparadox.nicetohave.command

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.registry.CommandRegistry
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import redstoneparadox.nicetohave.hooks.CommandConfirmationHolder
import redstoneparadox.nicetohave.util.config.OldConfig

object Commands {

    fun initCommands() = CommandRegistry.INSTANCE.register(false) { dispatcher: CommandDispatcher<ServerCommandSource>? ->
        val acceptNode = CommandManager
                .literal("yes")
                .executes { context ->
                    val player = context.source.player
                    (player as CommandConfirmationHolder).confirmation.accept(context)
                    player.clearCommandConfirmation()
                    1
                }
                .build()

        val declineNode = CommandManager
                .literal("no")
                .executes { context ->
                    val player = context.source.player
                    (player as CommandConfirmationHolder).clearCommandConfirmation()
                    1
                }
                .build()

        dispatcher?.root?.addChild(acceptNode)
        dispatcher?.root?.addChild(declineNode)

        if (OldConfig.Misc.stuckCommand) {
            val stuckNode = CommandManager
                    .literal("stuck")
                    .executes(ConfirmableCommand(StuckCommand(), "(You will die and respawn.)"))
                    .build()
            dispatcher?.root?.addChild(stuckNode)
        }
    }
}