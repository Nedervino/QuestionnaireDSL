package ql;

import ql.ast.Form;
import ql.parser.FormBuilder;
import ql.utilities.IOHandler;

import java.io.File;

public class BaseQlTest {

    protected static Form createForm(String fileName) {
        File formContent = IOHandler.loadFile(fileName);
        return FormBuilder.createForm(formContent);
    }

}

