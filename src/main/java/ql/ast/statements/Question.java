package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import ql.ast.visitors.StatementVisitor;

public class Question extends Statement {

    private String label;
    private String identifier;
    private Type type;

    public Question(String identifier, String label, Type type, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
