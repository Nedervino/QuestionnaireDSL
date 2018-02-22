package AST;

public class InputNode extends ASTNode {

    String label;
    String id;
    String type;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitInput(this);
    }

}
