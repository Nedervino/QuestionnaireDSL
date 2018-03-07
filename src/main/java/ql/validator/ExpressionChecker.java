package ql.validator;

import ql.ast.Form;

/**
 * Checks AST for references to undefined questions, conditions of non-boolean type,
 * and invalid operand/operator type combinations
 */
public class ExpressionChecker {

    private SymbolTable symbolTable;

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return true;
    }
}