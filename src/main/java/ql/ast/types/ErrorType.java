package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class ErrorType extends Type {

    public ErrorType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String toString() {
        return "error";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
            return visitor.visit(this);
        }

}
