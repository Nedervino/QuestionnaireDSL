package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.types.Type;
import ql.ast.visitors.FormStatementVisitor;

public class ComputedQuestion extends Question {

    private final Expression expression;

    public ComputedQuestion(String identifier, String label, Type type, Expression expression, SourceLocation sourceLocation) {
        super(identifier, label, type, sourceLocation);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(FormStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}