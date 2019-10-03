package redstoneparadox.nicetohave.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource

class CommandConfirmation(private val toExecute: Command<ServerCommandSource>) {

    fun accept(context: CommandContext<ServerCommandSource>?) = toExecute.run(context)
}