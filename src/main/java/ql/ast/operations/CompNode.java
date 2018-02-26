package ql.ast.operations;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class CompNode extends ASTNode {

    public String symbol;
    public ASTNode first;
    public ASTNode second;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitComp(this);
    }

}
