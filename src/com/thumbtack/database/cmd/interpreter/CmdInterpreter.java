package com.thumbtack.database.cmd.interpreter;

import com.thumbtack.database.cmd.executor.Cmd;

public interface CmdInterpreter {
    public Cmd getExecutableCommand(String rawCommand);
}
