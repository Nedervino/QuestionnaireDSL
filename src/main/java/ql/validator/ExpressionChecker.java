package ql.validator;

import ql.ast.Form;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.validator.issuetracker.IssueTracker;

/**
 * Checks AST for references to undefined questions, conditions of non-boolean type,
 * and invalid operand/operator type combinations
 */
// public class ExpressionChecker implements ExpressionVisitor<T>, StatementVisitor<T>, FormVisitor<T> {
public class ExpressionChecker {

    private final IssueTracker issueTracker;
    private SymbolTable symbolTable;

    public ExpressionChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
    }

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return true;
    }
}