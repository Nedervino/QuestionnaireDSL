package ql.ast.expressions;

import ql.ast.ASTVisitor;

public class ExprNumNode extends ExprNode {

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprNum(this);
    }

}
