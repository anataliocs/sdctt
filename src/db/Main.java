package db;

import db.commands.interpreter.CommandInterpreter;
import db.commands.interpreter.BasicCommandInterpreter;
import db.reader.Reader;
import db.reader.StdinReaderImpl;
/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

 */


public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        Reader reader = new StdinReaderImpl();
        CommandInterpreter parser = new BasicCommandInterpreter();

        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase(parser);

        while (true) {
            String rawCommand = reader.getRawCommand();
            if(DEBUG)
                System.out.println("rawCommand = " + rawCommand);
            inMemoryDatabase.executeCommand(rawCommand);
        }
    }
}
