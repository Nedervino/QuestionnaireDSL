package ql.ast.types;

import ql.ast.SourceLocation;

public abstract class NumberType extends Type {

    protected NumberType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

}
