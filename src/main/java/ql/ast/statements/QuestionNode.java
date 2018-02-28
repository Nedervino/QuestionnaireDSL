package ql.ast.statements;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.types.Type;

public class QuestionNode extends ASTNode {

    private String label;
    private String id;
    private Type type;

    public QuestionNode(String id, String label, Type type) {
        this.id = id;
        this.label = label;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public <T> T accept(ASTVisitor<? extends T> visitor){
        return visitor.visitQuestion(this);
    }
}
