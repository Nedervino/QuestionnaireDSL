package ql.ast.values;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ValBoolNode extends ASTNode {

    String content;

    public ValBoolNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitValBool(this);
    }
}
