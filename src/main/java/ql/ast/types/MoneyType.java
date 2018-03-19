package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

import java.util.Objects;

public class MoneyType extends NumberType {

    public MoneyType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public String toString() {
        return "money";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isOfType(String type) {
        if (Objects.equals(type, "numeric")) {
            return true;
        } else {
            return toString().equals(type);
        }
    }

}
