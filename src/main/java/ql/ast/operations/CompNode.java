package ql.ast.operations;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class CompNode extends ASTNode {

    private String symbol;
    private ASTNode first;
    private ASTNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComp(this);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ASTNode getFirst() {
        return first;
    }

    public void setFirst(ASTNode first) {
        this.first = first;
    }

    public ASTNode getSecond() {
        return second;
    }

    public void setSecond(ASTNode second) {
        this.second = second;
    }
}
