package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public abstract class ExprNode extends ASTNode {

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExpr(this);
    }

}
