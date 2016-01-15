package database.cmd.interpreter;

import database.cmd.executor.Cmd;

public interface CmdInterpreter {
    public Cmd getCommand(String rawCommand);
}
