package ql.ast;

import java.util.ArrayList;

public class FormNode extends ASTNode {

    String label;
    ArrayList<ASTNode> block;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitForm(this);
    }

}
