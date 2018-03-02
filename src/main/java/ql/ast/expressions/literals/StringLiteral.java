package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class StringLiteral extends Expression{

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
}
