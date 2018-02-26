package ql.ast.values;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class ValStrNode extends ASTNode {

    public String content;

    public ValStrNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitValStr(this);
    }
}
