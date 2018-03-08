package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import ql.ast.visitors.StatementVisitor;

public class Question extends Statement {

    private String label;
    private String id;
    private Type type;

    public Question(String id, String label, Type type, SourceLocation sourceLocation) {
        super(sourceLocation);
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
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
