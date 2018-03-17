package ql;

import main.FileScanner;
import ql.ast.Form;
import ql.parser.ASTBuilder;

public class Helper {

    public Form buildASTFromFile(String fileName, ASTBuilder astBuilder) {
        String fileContent = new FileScanner().loadFile(fileName);
        return astBuilder.buildASTFromString(fileContent);
    }

}

