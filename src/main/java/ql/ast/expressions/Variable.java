package ql.ast.expressions;

import ql.ast.visitors.ExpressionVisitor;

public class Variable extends Expression {

    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
