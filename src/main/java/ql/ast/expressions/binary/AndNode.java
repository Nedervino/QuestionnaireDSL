package ql.ast.expressions.binary;

import ql.ast.visitors.ExpressionVisitor;

public class AndNode extends BinOpNode {

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }

}
