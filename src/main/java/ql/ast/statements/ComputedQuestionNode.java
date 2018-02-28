package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;
import ql.ast.types.Type;

public class ComputedQuestionNode extends QuestionNode {

    private ExprNode expr;

    public ComputedQuestionNode(String id, String label, Type type, ExprNode expr) {
        super(id, label, type);
        this.expr = expr;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComputedQuestion(this);
    }
}
