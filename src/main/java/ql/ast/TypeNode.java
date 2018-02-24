package ql.ast;

public class TypeNode extends ASTNode {

    String content;

    public TypeNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitType(this);
    }
}
