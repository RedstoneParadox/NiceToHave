package io.github.redstoneparadox.nicetohave.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import io.github.redstoneparadox.nicetohave.hooks.CommandConfirmationHolder

class ConfirmableCommand(private val onConfirm: Command<ServerCommandSource>, private val message: String): Command<ServerCommandSource> {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        val player = context?.source?.player
        if (player != null) {
            player.sendMessage(LiteralText("Are you sure you want to do that? $message. \n (Type '/yes' to confirm or '/no' to deny."), false)
            (player as CommandConfirmationHolder).setCommandConfirmation(CommandConfirmation(onConfirm))
            return 1
        }
        return 0
    }

}