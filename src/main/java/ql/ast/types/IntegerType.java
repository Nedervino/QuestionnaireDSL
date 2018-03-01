package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class IntegerType extends Type {

    @Override
    public String toString (){
        return "integer";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
