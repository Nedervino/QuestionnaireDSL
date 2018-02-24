package ql.ast;

import java.util.ArrayList;

public class ExprIfNode extends ASTNode {

    ExprNode cond;
    ArrayList<ASTNode> block;

    public ExprIfNode(){
        block = new ArrayList<ASTNode>();
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprIf(this);
    }

}
