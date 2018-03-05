package ql.ast.statements;

import ql.ast.expressions.Expression;

import java.util.List;

public class IfElseStatement extends IfStatement {

    private List<Statement> elseStatements;

    public IfElseStatement(Expression condition, List<Statement> ifStatements, List<Statement> elseStatements) {
        super(condition, ifStatements);
        this.elseStatements = elseStatements;
    }

    public List<Statement> getElseStatements() {
        return elseStatements;
    }

}
