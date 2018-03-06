package ql.validator;

import ql.ast.Form;

public class QuestionDuplicationChecker {

    private SymbolTable symbolTable;

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return true;
    }
}
