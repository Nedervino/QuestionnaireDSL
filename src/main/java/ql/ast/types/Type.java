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

    public boolean isCompatibleWith(Type type) {
        return this.toString().equals(type.toString()) ||
                (this.toString().equals("integer") && type.toString().equals("decimal")) ||
                (this.toString().equals("decimal") && type.toString().equals("integer"));
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
