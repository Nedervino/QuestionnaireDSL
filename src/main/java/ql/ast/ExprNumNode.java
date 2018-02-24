package ql.ast;

public class ExprNumNode extends ExprNode {

    String symbol;
    ExprNode first;
    ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprNum(this);
    }

}
