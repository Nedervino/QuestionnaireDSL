package ql.ast.values;

import ql.ast.ASTVisitor;
import ql.ast.expressions.ExprNode;

public class IDNode extends ExprNode {

    public String content;

    public IDNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitID(this);
    }
}
