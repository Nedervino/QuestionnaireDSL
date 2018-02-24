package ql.ast;

public class OutputNode extends ASTNode {

    String label;
    String id;
    String type;
    ExprNode expr;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitOutput(this);
    }
}
