package db.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderImpl implements Reader {
    private Scanner scanner;

    public FileReaderImpl(String fileLocation) {
        File file = new File(fileLocation);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public String getRawCommand() {
        return scanner.nextLine();
    }
}
