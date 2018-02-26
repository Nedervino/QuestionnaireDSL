package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class TypeNode extends ASTNode {

    public String content;

    public TypeNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitType(this);
    }
}
