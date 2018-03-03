package ql.ast.expressions;

import ql.ast.visitors.ExpressionVisitor;

public class IDNode extends Expression {

    private final String content;

    public IDNode(String content) {
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
