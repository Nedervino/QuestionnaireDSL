package ql.ast.expressions.values;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class ValNode extends ExprNode {

    private String content;

    public ValNode(String content) {
        this.setContent(content);
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitVal(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
