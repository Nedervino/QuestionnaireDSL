package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

import java.util.ArrayList;

public class IfStatementNode extends ASTNode {

    public ExprNode cond;
    public ArrayList<ASTNode> block;

    public IfStatementNode(){
        block = new ArrayList<ASTNode>();
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitIfStatement(this);
    }

}
