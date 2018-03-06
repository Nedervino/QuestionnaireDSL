package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class DateType extends Type {

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
