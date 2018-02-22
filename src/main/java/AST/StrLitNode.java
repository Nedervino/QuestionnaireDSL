package AST;

public class StrLitNode extends ASTNode {

    String content;

    public StrLitNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitStrLit(this);
    }
}
