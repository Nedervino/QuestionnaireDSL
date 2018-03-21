package ql.validator;

import ql.ast.Form;

public interface Checker {

    boolean passesTests(Form form, SymbolTable symbolTable);

}
