package ql.ast.expressions.unary;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class Negative extends UnaryOperation {

    public Negative(Expression expression, SourceLocation sourceLocation) {
        super(expression, sourceLocation);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
