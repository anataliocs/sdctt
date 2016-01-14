package db.commands.interpreter;

import db.commands.impl.Command;

public interface CommandInterpreter {
    public Command getCommand(String rawCommand);
}
