package ql.ast.expressions.binary;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;

public abstract class BinaryOperation extends Expression {

    private final Expression left;
    private final Expression right;

    public BinaryOperation(Expression left, Expression right, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

}
