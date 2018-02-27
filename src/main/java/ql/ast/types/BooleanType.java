package ql.ast.types;

import ql.ast.ASTNode;
import ql.ast.ASTVisitor;
import ql.ast.visitors.TypeVisitor;

public class BooleanType extends ASTNode {

    public String toString (){
        return "boolean";
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
