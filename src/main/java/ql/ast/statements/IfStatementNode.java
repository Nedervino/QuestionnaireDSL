package ql.ast.statements;

import ql.ast.expressions.Expression;
import ql.ast.visitors.StatementVisitor;

import java.util.List;

public class IfStatementNode extends Statement {

    private Expression condition;
    private List<Statement> statements;

    public IfStatementNode(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor){
        return visitor.visit(this);
    }

}
