package ql;

import main.FileScanner;
import ql.ast.Form;
import ql.parser.FormBuilder;

public class Helper {

    public Form buildASTFromFile(String fileName, FormBuilder formBuilder) {
        String fileContent = new FileScanner().loadFile(fileName);
        return formBuilder.buildASTFromString(fileContent);
    }

}

