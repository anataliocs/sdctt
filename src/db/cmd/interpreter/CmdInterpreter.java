package db.cmd.interpreter;

import db.cmd.impl.Cmd;

public interface CmdInterpreter {
    public Cmd getCommand(String rawCommand);
}
