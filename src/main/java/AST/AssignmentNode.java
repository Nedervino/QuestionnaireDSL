package AST;

public class AssignmentNode extends ASTNode {

    String id;
    String type;
    ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitAssignment(this);
    }

}
