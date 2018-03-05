package ql.ast.expressions;

import ql.ast.visitors.ExpressionVisitor;

public class Variable extends Expression {

    private final String content;

    public Variable(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
