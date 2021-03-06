package com.thumbtack.database.reader;

import java.util.Scanner;

public class BasicReaderImpl implements Reader {
    private final Scanner scanner;

    public BasicReaderImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getRawCommand() {
        return scanner.nextLine();
    }
}
