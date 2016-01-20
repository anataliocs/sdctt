package com.thumbtack.database;

import com.thumbtack.database.cmd.interpreter.BasicCmdInterpreter;
import com.thumbtack.database.cmd.interpreter.CmdInterpreter;
import com.thumbtack.database.reader.BasicReaderImpl;
import com.thumbtack.database.reader.Reader;

/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.com.thumbtack.com/challenges/simple-com.thumbtack.database

 */

class Application {
    private static final boolean DEBUG = false;

    private static final Reader reader;
    private static final CmdInterpreter parser;
    private static final InMemoryDatabase inMemoryDatabase;

    static {
        reader = new BasicReaderImpl();
        parser = new BasicCmdInterpreter();
        inMemoryDatabase = new InMemoryDatabase(parser);
    }

    public static void main(final String[] args) {

        while (true) {
            final String rawCommand = reader.getRawCommand();
            inMemoryDatabase.executeCommand(rawCommand);

            if (DEBUG)
                debug(rawCommand);
        }
    }

    private static void debug(String rawCommand) {
        System.out.println("rawCommand = " + rawCommand);
    }
}
