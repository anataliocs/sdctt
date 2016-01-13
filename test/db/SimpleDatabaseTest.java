package db;

import db.commands.parser.CommandParser;
import db.commands.parser.SimpleCommandParser;
import org.junit.*;

public class SimpleDatabaseTest {
    InMemoryDatabase inMemoryDatabase;

    @Before
    public void setUp() throws Exception {
        CommandParser parser = new SimpleCommandParser();
        inMemoryDatabase = new InMemoryDatabase(parser);
    }

}
