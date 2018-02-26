package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;

public class QuestionNode extends ASTNode {

    private String label;
    private String id;
    private String type;

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitQuestion(this);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
