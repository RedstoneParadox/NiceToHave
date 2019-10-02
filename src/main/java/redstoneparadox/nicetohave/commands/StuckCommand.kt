package redstoneparadox.nicetohave.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import redstoneparadox.nicetohave.util.config.Config

class StuckCommand: Command<ServerCommandSource> {
    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        if (Config.Misc.stuckCommand) {
            val player = context?.source?.player
            if (player != null) {
                player.kill()
                return 1
            }
        }
        return 0
    }
}