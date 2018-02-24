package ql.ast;

public class OpNode extends ASTNode {

    String content;

    public OpNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOp(this);
    }
}
