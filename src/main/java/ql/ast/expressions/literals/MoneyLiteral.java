package ql.ast.expressions.literals;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

import java.math.BigDecimal;

public class MoneyLiteral extends Expression {

    private final BigDecimal value;

    public MoneyLiteral(String value, SourceLocation sourceLocation) {
        super(sourceLocation);
        // this.value = Double.parseDouble(value.replace(',', '.'));
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
