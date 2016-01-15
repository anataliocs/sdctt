package database;

import database.cmd.executor.Cmd;
import database.cmd.interpreter.CmdInterpreter;
import database.data.DataWrapper;

/**
 * The database wrapper that receives a command and executes it
 */
public class InMemoryDatabase {
    private final CmdInterpreter parser;
    private DataWrapper dataWrapper = new DataWrapper();

    public InMemoryDatabase(CmdInterpreter parser) {
        this.parser = parser;
    }

    public void executeCommand(String rawCommand) {
        Cmd cmd = parser.getCommand(rawCommand);
        cmd.execute(dataWrapper);
    }
}
