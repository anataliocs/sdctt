package com.thumbtack.database;

import com.thumbtack.database.cmd.interpreter.BasicCmdInterpreter;
import com.thumbtack.database.cmd.interpreter.CmdInterpreter;
import com.thumbtack.database.reader.BasicReaderImpl;
import com.thumbtack.database.reader.Reader;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
/   Chris Anatalio
    Thumbtack Simple Database Challenge - https://www.com.thumbtack.com/challenges/simple-com.thumbtack.database

 */

class Application {

    private static final Reader reader;
    private static final CmdInterpreter parser;
    private static final InMemoryDatabase inMemoryDatabase;

    static {
        reader = new BasicReaderImpl();
        parser = new BasicCmdInterpreter();
        inMemoryDatabase = new InMemoryDatabase(parser);
    }

    public static void main(final String[] args) {

        //Read commands from file
        if(args != null && args.length > 0) {

            Path filePath = Paths.get(args[0]);
            List<String> lines = new ArrayList<>();
            try {
                lines = Files.lines(filePath).collect(
                        Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }

            lines.stream().forEach( cmd ->
                    inMemoryDatabase.executeCommand(cmd));
        }

        //Else read from standard input
        else {
            while (true) {
                final String rawCommand = reader.getRawCommand();
                inMemoryDatabase.executeCommand(rawCommand);
            }
        }
    }
}
