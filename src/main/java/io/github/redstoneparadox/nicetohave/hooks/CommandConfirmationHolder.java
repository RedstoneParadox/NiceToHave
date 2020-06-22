package io.github.redstoneparadox.nicetohave.hooks;

import io.github.redstoneparadox.nicetohave.command.CommandConfirmation;

public interface CommandConfirmationHolder {

    CommandConfirmation getConfirmation();

    void setCommandConfirmation(CommandConfirmation confirmation);

    void clearCommandConfirmation();
}
