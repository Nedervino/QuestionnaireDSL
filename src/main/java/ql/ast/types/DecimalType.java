package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class DecimalType extends NumberType {

    public DecimalType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String toString() {
        return "decimal";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
