package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class DeclarationNode extends ASTNode {

    public String id;
    public String type;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitDeclaration(this);
    }

}
