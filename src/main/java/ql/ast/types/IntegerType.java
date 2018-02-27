package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class IntegerType extends Type {

    public String toString (){
        return "integer";
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
