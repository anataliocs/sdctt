package db;

import db.cmd.impl.Cmd;
import db.cmd.interpreter.CmdInterpreter;
import db.data.DataWrapper;

/**
 * The database wrapper that receives a command and executes it
 */
public class InMemoryDatabase {
    private DataWrapper dataWrapper = new DataWrapper();
    private final CmdInterpreter parser;

    public InMemoryDatabase(CmdInterpreter parser) {
        this.parser = parser;
    }

    public void executeCommand(String rawCommand) {
        Cmd cmd = parser.getCommand(rawCommand);
        cmd.execute(dataWrapper);
    }
}
