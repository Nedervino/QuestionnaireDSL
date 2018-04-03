package ql.utilities;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOHandler {

    public static byte[] loadFile(String filePath) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Couldn't process input source: " + e.getMessage());
        }
        return fileContent;
    }

    public static byte[] loadFileUsingDialog(String allowedExtension) {
        FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setFilenameFilter((dir, name) -> name.endsWith(allowedExtension));
        fd.setVisible(true);
        String fileName = fd.getFile();
        if (fileName == null) {
            System.out.println("No file selected");
            System.exit(0);
        } else {
            String absolutePath = fd.getDirectory().concat(fileName);
            byte[] formContent = IOHandler.loadFile(absolutePath);
            return formContent;
        }
        return null;
    }

}
