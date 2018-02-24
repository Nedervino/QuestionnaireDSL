package ql.ast;

public class StrNode extends ASTNode {

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitStr(this);
    }

}
