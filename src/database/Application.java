package database;

import database.cmd.interpreter.BasicCmdInterpreter;
import database.cmd.interpreter.CmdInterpreter;
import database.reader.BasicReaderImpl;
import database.reader.Reader;

/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

 */


public class Application {
    private static final boolean DEBUG = false;

    private static Reader reader;
    private static CmdInterpreter parser;
    private static InMemoryDatabase inMemoryDatabase;

    static {
        reader = new BasicReaderImpl();
        parser = new BasicCmdInterpreter();
        inMemoryDatabase = new InMemoryDatabase(parser);
    }

    public static void main(String[] args) {

        while (true) {
            String rawCommand = reader.getRawCommand();
            inMemoryDatabase.executeCommand(rawCommand);

            if (DEBUG)
                debug(rawCommand);
        }
    }

    public static void debug(String rawCommand) {
        System.out.println("rawCommand = " + rawCommand);
    }
}
