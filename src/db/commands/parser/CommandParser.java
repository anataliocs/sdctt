package db.commands.parser;

import db.commands.impl.Command;

public interface CommandParser {
    public Command getCommand(String rawCommand);
}
