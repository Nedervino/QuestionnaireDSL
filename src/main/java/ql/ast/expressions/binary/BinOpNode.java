package ql.ast.expressions.binary;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.Expression;

public abstract class BinOpNode extends Expression {

    private Expression first;
    private Expression second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitBinOp(this);
    }

    public ASTNode getFirst() {
        return first;
    }

    public void setFirst(Expression first) {
        this.first = first;
    }

    public ASTNode getSecond() {
        return second;
    }

    public void setSecond(Expression second) {
        this.second = second;
    }
}
