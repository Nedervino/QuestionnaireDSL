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
    public boolean isOfType(Type type) {
        return false;
    }

    public boolean isOfType(BooleanType type) {
        return true;
    }

    // @Override
    // public boolean equals(Type t) {
    //     return t.isDecimal();
    // }
    //
    // @Override
    // public boolean isDecimal() {
    //     return true;
    // }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
