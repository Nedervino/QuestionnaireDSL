package ql.ast.expressions.unary;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;

public abstract class UnaryOperation extends Expression {

    private Expression expression;

    public UnaryOperation(Expression expression, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}
