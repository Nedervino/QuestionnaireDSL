package ql.ast.operations;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class OpNode extends ASTNode {

    public String content;

    public OpNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOp(this);
    }
}
