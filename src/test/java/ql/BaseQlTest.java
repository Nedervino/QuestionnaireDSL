package ql;

import ql.ast.Form;
import ql.utilities.IOHandler;
import ql.parser.FormBuilder;

public class BaseQlTest {

    protected static Form createForm(String fileName) {
        String fileContent = IOHandler.loadFile(fileName);
        return new FormBuilder().createForm(fileContent);
    }

}

