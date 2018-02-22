package AST;

public class ExprNode extends ASTNode {

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExpr(this);
    }

}
