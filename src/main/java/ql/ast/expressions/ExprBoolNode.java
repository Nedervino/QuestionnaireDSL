package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ExprBoolNode extends ASTNode {

    public String symbol;
    public ExprNode first;
    public ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprBool(this);
    }

}
