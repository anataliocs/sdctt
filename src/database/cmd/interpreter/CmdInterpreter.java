package database.cmd.interpreter;

import database.cmd.impl.Cmd;

public interface CmdInterpreter {
    public Cmd getCommand(String rawCommand);
}
