package db;

import db.commands.impl.Command;
import db.commands.interpreter.CommandInterpreter;
import db.data.DataWrapper;

/**
 * The database wrapper that receives a command and executes it
 */
public class InMemoryDatabase {
    private DataWrapper dataWrapper = new DataWrapper();
    private final CommandInterpreter parser;

    public InMemoryDatabase(CommandInterpreter parser) {
        this.parser = parser;
    }

    public void executeCommand(String rawCommand) {
        Command command = parser.getCommand(rawCommand);
        command.execute(dataWrapper);
    }
}
