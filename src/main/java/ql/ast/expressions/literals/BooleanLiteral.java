package ql.ast.expressions.literals;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class BooleanLiteral extends Expression {

    private final boolean value;

    public BooleanLiteral(String value, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.value = Boolean.parseBoolean(value);
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
