package ql;

import main.FileScanner;
import ql.ast.Form;
import ql.parser.FormBuilder;

public class BaseQlTest {

    public static Form createForm(String fileName) {
        String fileContent = new FileScanner().loadFile(fileName);
        return new FormBuilder().createForm(fileContent);
    }

}

