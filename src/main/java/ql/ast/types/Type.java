package ql.ast.types;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import ql.ast.visitors.TypeVisitor;

public abstract class Type extends ASTNode {

    public Type(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract String toString();

    public boolean isOfType(String type) {
        return toString().equals(type);
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isCompatibleWith(Type otherType) {
        if(otherType.isNumeric()) {
            return this.isNumeric();
        } else {
            return this.toString().equals(otherType.toString());
        }
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
