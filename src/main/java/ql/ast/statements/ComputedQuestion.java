package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.ast.types.Type;
import ql.ast.visitors.StatementVisitor;

public class ComputedQuestion extends Question {

    private Expression expression;

    public ComputedQuestion(String id, String label, Type type, Expression expression) {
        super(id, label, type);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}