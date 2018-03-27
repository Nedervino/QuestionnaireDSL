package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class DateType extends Type {

    public DateType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String getType() {
        return "date";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
