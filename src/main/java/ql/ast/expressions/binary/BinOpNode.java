package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public abstract class BinOpNode extends Expression {

    private final Expression left;
    private final Expression right;

    public BinOpNode(Expression left, Expression right) {
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
