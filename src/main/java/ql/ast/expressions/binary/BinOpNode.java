package ql.ast.expressions.binary;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public abstract class BinOpNode extends ExprNode {

    protected ExprNode first;
    private ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitBinOp(this);
    }

    public ASTNode getFirst() {
        return first;
    }

    public void setFirst(ExprNode first) {
        this.first = first;
    }

    public ASTNode getSecond() {
        return second;
    }

    public void setSecond(ExprNode second) {
        this.second = second;
    }
}
