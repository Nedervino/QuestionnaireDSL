package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class IntegerType extends NumberType {

    public IntegerType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String toString() {
        return "integer";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
