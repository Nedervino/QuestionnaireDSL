package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class StringType extends Type {

    public StringType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
