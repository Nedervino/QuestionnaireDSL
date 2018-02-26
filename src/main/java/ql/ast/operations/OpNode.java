package ql.ast.operations;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class OpNode extends ASTNode {

    private String content;

    public OpNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOp(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
