package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class NotEqual extends BinaryOperation {

    public NotEqual(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
