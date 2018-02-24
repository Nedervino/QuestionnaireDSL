package ql.ast;

public class IDNode extends ExprNode {

    String content;

    public IDNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitID(this);
    }
}
