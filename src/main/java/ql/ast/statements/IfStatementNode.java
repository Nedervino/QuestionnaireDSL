package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.ast.visitors.StatementVisitor;

import java.util.List;

public class IfStatementNode extends Statement {

    private Expression condition;
    private List<Statement> ifStatements;

    public IfStatementNode(Expression condition, List<Statement> ifStatements) {
        this.condition = condition;
        this.ifStatements = ifStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getIfStatements() {
        return ifStatements;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
