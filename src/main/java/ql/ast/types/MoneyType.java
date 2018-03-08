package ql.ast.types;

import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public class MoneyType extends Type {

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

}
