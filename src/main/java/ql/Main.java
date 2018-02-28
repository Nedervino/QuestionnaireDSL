package ql;

import ql.parser.ASTBuilder;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        String fileName = "src/input/ql/formIf.ql";
        ASTBuilder astBuilder = new ASTBuilder();
        try {
            astBuilder.buildASTFromString(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}