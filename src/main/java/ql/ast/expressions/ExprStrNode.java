package ql.ast.expressions;

import ql.ast.ASTVisitor;

public class ExprStrNode extends ExprNode {



    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprStr(this);
    }

}
