package ql.ast;

public class ValIntNode extends ASTNode {

    String content;

    public ValIntNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitInt(this);
    }

}
