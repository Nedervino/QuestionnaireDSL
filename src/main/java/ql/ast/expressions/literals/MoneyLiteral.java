package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class MoneyLiteral extends Expression {

    private final double value;

    public MoneyLiteral(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
