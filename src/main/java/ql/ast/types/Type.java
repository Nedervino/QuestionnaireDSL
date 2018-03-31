package ql.ast.types;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public abstract class Type extends ASTNode {

    protected Type(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract String getType();

    public boolean isOfType(String type) {
        return getType().equals(type);
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isCompatibleWith(Type otherType) {
        if (otherType.isNumeric()) {
            return this.isNumeric();
        } else {
            return this.getType().equals(otherType.getType());
        }
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
