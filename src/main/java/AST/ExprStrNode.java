package AST;

public class ExprStrNode extends ExprNode {

    String symbol;
    ExprNode first;
    ExprNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitExprStr(this);
    }

}
