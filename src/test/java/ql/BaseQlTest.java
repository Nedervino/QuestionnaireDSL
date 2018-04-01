package ql;

import ql.ast.Form;
import ql.main.FileScanner;
import ql.parser.FormBuilder;

public class BaseQlTest {

    protected static Form createForm(String fileName) {
        String fileContent = new FileScanner().loadFile(fileName);
        return new FormBuilder().createForm(fileContent);
    }

}

