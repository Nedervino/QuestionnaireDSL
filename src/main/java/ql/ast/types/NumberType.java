package ql.ast.types;

import ql.ast.SourceLocation;

public abstract class NumberType extends Type {

    @Override
    public boolean isNumeric() {
        return true;
    }

    public NumberType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

}
