package ql.ast.expressions.binary;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class GreaterThan extends BinaryOperation {

    public GreaterThan(Expression left, Expression right, SourceLocation sourceLocation) {
        super(left, right, sourceLocation);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
