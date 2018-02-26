package ql.ast.values;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ValStrNode extends ASTNode {

    private String content;

    public ValStrNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitValStr(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
