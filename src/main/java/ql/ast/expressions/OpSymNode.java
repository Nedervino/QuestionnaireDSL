package ql.ast.expressions;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class OpSymNode extends ASTNode {

    private String content;

    public OpSymNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOpSym(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
