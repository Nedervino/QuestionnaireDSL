package ql.ast.expressions.unary;

import ql.ast.expressions.Expression;
import ql.ast.visitors.ExpressionVisitor;

public class LogicalNegation extends UnaryOperation {

    public LogicalNegation(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
