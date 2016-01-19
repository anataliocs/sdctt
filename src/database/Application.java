package database;

import database.cmd.interpreter.BasicCmdInterpreter;
import database.cmd.interpreter.CmdInterpreter;
import database.reader.BasicReaderImpl;
import database.reader.Reader;

/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

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
