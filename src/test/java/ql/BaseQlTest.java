package ql;

import ql.ast.Form;
import ql.parser.FormBuilder;
import ql.utilities.IOHandler;

public class BaseQlTest {

    protected static Form createForm(String fileName) {
        String fileContent = IOHandler.loadFile(fileName);
        return FormBuilder.createForm(fileContent);
    }

}

