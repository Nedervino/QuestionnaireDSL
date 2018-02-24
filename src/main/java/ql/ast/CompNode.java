package ql.ast;

public class CompNode extends ASTNode {

    String symbol;
    ASTNode first;
    ASTNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComp(this);
    }

}
