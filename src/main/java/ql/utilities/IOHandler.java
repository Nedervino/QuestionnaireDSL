package ql.utilities;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class IOHandler {

    public static File loadFile(String filePath) {
        return new File(filePath);
    }

    public static File loadFileUsingDialog(String allowedExtension) {
        FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setFilenameFilter((dir, name) -> name.endsWith(allowedExtension));
        fd.setVisible(true);
        String fileName = fd.getFile();
        if (fileName == null) {
            System.out.println("No file selected");
            System.exit(0);
        } else {
            String absolutePath = fd.getDirectory().concat(fileName);
            File formContent = IOHandler.loadFile(absolutePath);
            return formContent;
        }
        return null;
    }

    public static String toString(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.err.println("Couldn't process input source: " + e.getMessage());
        }
        return null;
    }
}
