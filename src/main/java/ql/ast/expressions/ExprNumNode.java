package ql.ast.expressions;

import ql.ast.ASTVisitor;

public class ExprNumNode extends ExprNode {

    public String symbol;
    public ExprNode first;
    public ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprNum(this);
    }

}
