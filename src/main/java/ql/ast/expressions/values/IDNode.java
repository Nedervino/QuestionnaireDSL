package ql.ast.expressions.values;

import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class IDNode extends ExprNode {

    private String content;

    public IDNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitID(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
