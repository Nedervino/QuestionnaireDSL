package AST;

public class ExprBoolNode extends ASTNode {

    String symbol;
    ExprNode first;
    ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprBool(this);
    }

}
