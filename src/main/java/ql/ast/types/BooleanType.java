package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class BooleanType extends Type {

    public BooleanType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
