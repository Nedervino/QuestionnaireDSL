package ql.ast.statements;

import ql.ast.types.Type;
import ql.ast.visitors.StatementVisitor;

public class QuestionNode extends Statement {

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

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor){
        return visitor.visit(this);
    }

}
