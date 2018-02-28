package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class UnOpNode extends ExprNode {

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitUnOp(this);
    }

}
