package ql.ast.expressions.unary;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class UnOpNode extends ExprNode {

    private ExprNode term;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitUnOp(this);
    }

    public ExprNode getTerm() {
        return term;
    }

    public void setTerm(ExprNode first) {
        this.term = term;
    }
}
