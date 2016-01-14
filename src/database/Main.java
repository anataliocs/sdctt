package database;

import database.cmd.interpreter.CmdInterpreter;
import database.cmd.interpreter.BasicCmdInterpreter;
import database.reader.Reader;
import database.reader.StdinReaderImpl;
/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

 */


public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        Reader reader = new StdinReaderImpl();
        CmdInterpreter parser = new BasicCmdInterpreter();

        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase(parser);

        while (true) {
            String rawCommand = reader.getRawCommand();
            if(DEBUG)
                System.out.println("rawCommand = " + rawCommand);
            inMemoryDatabase.executeCommand(rawCommand);
        }
    }
}
