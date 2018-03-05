package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class LessThan extends BinaryOperation {

    public LessThan(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

