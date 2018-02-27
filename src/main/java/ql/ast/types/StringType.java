package ql.ast.types;

import ql.ast.visitors.TypeVisitor;

public class StringType extends Type {

    public String toString (){
        return "string";
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
