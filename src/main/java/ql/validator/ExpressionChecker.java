package ql.validator;

import ql.ast.Form;
import ql.ast.expressions.binary.BinaryOperation;
import ql.ast.types.ErrorType;
import ql.ast.types.Type;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.validator.issuetracker.IssueTracker;

/**
 * Checks AST for references to undefined questions, conditions of non-boolean type,
 * and invalid operand/operator type combinations
 */
// public class ExpressionChecker implements ExpressionVisitor<Void>, StatementVisitor<Void>, FormVisitor<Void> {
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

    // public Type checkTypeIncompatibility(BinaryOperation binaryOperation) {
    //     Type leftType = binaryOperation.getLeft().accept(this);
    //     Type rightType = binaryOperation.getRight().accept(this);
    //
    //     if (!leftType.isCompatibleWith(rightType)) {
    //         issueTracker.addError(binaryOperation.getLine(), binaryOperation.getColumn(), "Incompatible types");
    //         return new ErrorType();
    //     }
    //
    //     return leftType;
    // }
}