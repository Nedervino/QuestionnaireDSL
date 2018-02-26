package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class DeclarationNode extends ASTNode {

    private String id;
    private String type;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitDeclaration(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
