package ql.ast.types;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.visitors.TypeVisitor;

public class BooleanType extends Type {

    @Override
    public String toString (){
        return "boolean";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
