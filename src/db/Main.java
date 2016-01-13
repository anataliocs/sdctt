package db;

import db.commands.parser.CommandParser;
import db.commands.parser.SimpleCommandParser;
import db.reader.Reader;
import db.reader.StdinReaderImpl;
/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

 */


public class Main {
    public static void main(String[] args) {
        Reader reader = new StdinReaderImpl();
        CommandParser parser = new SimpleCommandParser();

        System.out.println("test");

        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase(parser);

        while (true) {
            String rawCommand = reader.getRawCommand();
            System.out.println("rawCommand = " + rawCommand);
            inMemoryDatabase.executeCommand(rawCommand);
        }
    }
}
