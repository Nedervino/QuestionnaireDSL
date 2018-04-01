package ql.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileScanner {

    public String loadFile(String filePath) {
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Couldn't process input source: " + e.getMessage());
        }
        return fileContent;
    }

}
