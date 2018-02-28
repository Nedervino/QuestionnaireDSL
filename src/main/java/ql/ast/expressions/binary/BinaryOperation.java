package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public abstract class BinaryOperation extends Expression {

    private final Expression right;
    private final Expression left;


    BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getRight() {
        return this.right;
    }

    public Expression getLeft() {
        return this.left;
    }
}
