package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class TypeNode extends ASTNode {

    private String content;

    public TypeNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitType(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
