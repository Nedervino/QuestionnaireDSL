package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

import java.util.List;

public class IfStatementNode extends ASTNode {

    private ExprNode condition;
    private List<Statement> statements;

    public IfStatementNode(ExprNode condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitIfStatement(this);
    }
}
