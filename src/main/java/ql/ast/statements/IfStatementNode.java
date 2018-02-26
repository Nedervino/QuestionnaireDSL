package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

import java.util.ArrayList;

public class IfStatementNode extends ASTNode {

    private ExprNode cond;
    private ArrayList<ASTNode> block;

    public IfStatementNode(){
        setBlock(new ArrayList<ASTNode>());
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitIfStatement(this);
    }

    public ExprNode getCond() {
        return cond;
    }

    public void setCond(ExprNode cond) {
        this.cond = cond;
    }

    public ArrayList<ASTNode> getBlock() {
        return block;
    }

    public void setBlock(ArrayList<ASTNode> block) {
        this.block = block;
    }
}
