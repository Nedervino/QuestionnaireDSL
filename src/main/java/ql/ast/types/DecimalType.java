package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class DecimalType extends Type {

    public String toString (){
        return "decimal";
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
