package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class BooleanLiteral extends Expression {

    private final boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
