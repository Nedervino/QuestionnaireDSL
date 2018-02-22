package AST;

public class DeclarationNode extends ASTNode {

    String id;
    String type;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitDeclaration(this);
    }

}
