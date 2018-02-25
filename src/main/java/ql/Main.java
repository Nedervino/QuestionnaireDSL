package ql;

import ql.parser.ASTBuilder;

public class Main {
    public static void main (String[] args) {
        String fileName = "src/input/ql/formIf.ql";
        ASTBuilder formGenerator = new ASTBuilder();
        formGenerator.loadFile(fileName);
    }
}