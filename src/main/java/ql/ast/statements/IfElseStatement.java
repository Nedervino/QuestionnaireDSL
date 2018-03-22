package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.expressions.Expression;
import ql.ast.visitors.FormStatementVisitor;

import java.util.List;

public class IfElseStatement extends IfStatement {

    private final List<Statement> elseStatements;

    public IfElseStatement(Expression condition, List<Statement> ifStatements, List<Statement> elseStatements, SourceLocation sourceLocation) {
        super(condition, ifStatements, sourceLocation);
        this.elseStatements = elseStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }


    @Override
    public <T> T accept(FormStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
