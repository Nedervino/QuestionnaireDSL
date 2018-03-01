package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class DecimalType extends Type {

    @Override
    public String toString (){
        return "decimal";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
