package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class IntegerLiteral extends Expression{

    private final int value;

    public IntegerLiteral(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
}
