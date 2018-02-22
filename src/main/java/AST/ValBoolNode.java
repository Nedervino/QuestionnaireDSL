package AST;

public class ValBoolNode extends ASTNode {

    String content;

    public ValBoolNode(String content) {
        this.content = content;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitBool(this);
    }
}
