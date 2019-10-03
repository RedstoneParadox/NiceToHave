package redstoneparadox.nicetohave.hooks;

import redstoneparadox.nicetohave.command.CommandConfirmation;

public interface CommandConfirmationHolder {

    CommandConfirmation getConfirmation();

    void setCommandConfirmation(CommandConfirmation confirmation);

    void clearCommandConfirmation();
}
