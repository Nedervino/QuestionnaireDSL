package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class StringType extends Type {

    @Override
    public String toString (){
        return "string";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
