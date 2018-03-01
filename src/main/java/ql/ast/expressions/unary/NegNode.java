package ql.ast.expressions.unary;

import ql.ast.visitors.ExpressionVisitor;

public class NegNode extends UnOpNode {

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }

}
