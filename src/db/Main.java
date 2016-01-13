package db;

import db.commands.parser.CommandParser;
import db.commands.parser.SimpleCommandParser;
import db.reader.IReader;
import db.reader.StdinReader;
/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.thumbtack.com/challenges/simple-database

 */


public class Main {
    public static void main(String[] args) {
        IReader reader = new StdinReader();
        CommandParser parser = new SimpleCommandParser();

        System.out.println("test");

        //injected parser into inMemoryDatabase, this way we have support for implementing a different command structure
        InMemoryDatabase inMemoryDatabase = new InMemoryDatabase(parser);

        while (true) {
            String rawCommand = reader.getRawCommand();
            System.out.println("rawCommand = " + rawCommand);
            inMemoryDatabase.executeCommand(rawCommand);
        }
    }
}
