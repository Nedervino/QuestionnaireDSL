package ql.ast.types;

import ql.ast.SourceLocation;

public abstract class NumberType extends Type {

    public NumberType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public boolean isCompatibleWith(NumberType type) {
        return true;
    }

}
