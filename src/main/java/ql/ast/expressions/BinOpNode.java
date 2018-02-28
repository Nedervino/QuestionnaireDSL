package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class BinOpNode extends ExprNode {

    private ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitBinOp(this);
    }

    public ASTNode getFirst() {
        return getTerm();
    }

    public void setFirst(ExprNode first) {
        setTerm(first);
    }

    public ASTNode getSecond() {
        return second;
    }

    public void setSecond(ExprNode second) {
        this.second = second;
    }
}
