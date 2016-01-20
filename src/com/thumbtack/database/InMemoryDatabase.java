package com.thumbtack.database;

import com.thumbtack.database.cmd.executor.Cmd;
import com.thumbtack.database.cmd.interpreter.CmdInterpreter;
import com.thumbtack.database.data.DataWrapper;

/**
 * The com.thumbtack.database wrapper that receives a command and executes it
 */
class InMemoryDatabase {
    private final CmdInterpreter parser;
    private final DataWrapper dataWrapper;

    {
        dataWrapper = new DataWrapper();
    }

    public InMemoryDatabase(final CmdInterpreter parser) {
        this.parser = parser;
    }

    public void executeCommand(final String rawCommand) {
        final Cmd cmd = parser.getCommand(rawCommand);
        cmd.execute(dataWrapper);
    }
}
