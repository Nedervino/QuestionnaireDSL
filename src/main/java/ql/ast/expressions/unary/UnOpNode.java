package ql.ast.expressions.unary;

import ql.ast.expressions.Expression;

public abstract class UnOpNode extends Expression {

    private Expression expression;

    public UnOpNode(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

}
