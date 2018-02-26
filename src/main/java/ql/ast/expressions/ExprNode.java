package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ExprNode extends ASTNode {

    private String symbol;
    private ExprNode first;
    private ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExpr(this);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ExprNode getFirst() {
        return first;
    }

    public void setFirst(ExprNode first) {
        this.first = first;
    }

    public ExprNode getSecond() {
        return second;
    }

    public void setSecond(ExprNode second) {
        this.second = second;
    }
}
