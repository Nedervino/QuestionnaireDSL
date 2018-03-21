package ql.validator.checkers;

import ql.ast.Form;
import ql.validator.SymbolTable;

public interface Checker {

    boolean passesTests(Form form, SymbolTable symbolTable);

}
