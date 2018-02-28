package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ExprNode extends ASTNode {

    private String symbol;
    protected ExprNode term;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExpr(this);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ExprNode getTerm() {
        return term;
    }

    public void setTerm(ExprNode first) {
        this.term = term;
    }

}
