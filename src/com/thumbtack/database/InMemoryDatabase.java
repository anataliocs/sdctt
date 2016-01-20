package com.thumbtack.database;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.cmd.interpreter.CmdInterpreter;
import com.thumbtack.database.data.DataWrapper;

/**
 * Receives and executes commands
 */
class InMemoryDatabase {
    private final CmdInterpreter cmdInterpreter;
    private final DataWrapper dataWrapper;

    {
        dataWrapper = new DataWrapper();
    }

    public InMemoryDatabase(final CmdInterpreter cmdInterpreter) {
        this.cmdInterpreter = cmdInterpreter;
    }

    public void executeCommand(final String rawCommand) {
        final Cmd cmd = cmdInterpreter.getExecutableCommand(rawCommand);
        cmd.execute(dataWrapper);
    }
}
