package ql.ast.expressions.literals;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyLiteral extends Expression {

    private final BigDecimal value;

    public MoneyLiteral(String value, SourceLocation sourceLocation) {
        super(sourceLocation);
        String parsableFloatValue = value.replace(',', '.');
        this.value = new BigDecimal(parsableFloatValue);
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getDisplayValue() {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
