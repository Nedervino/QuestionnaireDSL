package ql.ast.statements;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import ql.ast.visitors.FormStatementVisitor;

public class Question extends Statement {

    private final String label;
    private final String identifier;
    private final Type type;

    public Question(String identifier, String label, Type type, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.identifier = identifier;
        this.label = label;
        this.type = type;
    }

    public boolean isOfType(String otherType) {
        return this.type.isOfType(otherType);
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
    public <T> T accept(FormStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
