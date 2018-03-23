package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.FormStatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfStatement extends Statement {

    private final Expression condition;
    private final List<Statement> ifStatements;

    public IfStatement(Expression condition, List<Statement> ifStatements, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.condition = condition;
        this.ifStatements = ifStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getIfStatements() {
        return new ArrayList<>(ifStatements);
    }

    @Override
    public <T> T accept(FormStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
