package ql.ast.expressions.binary;

import ql.ast.visitors.ExpressionVisitor;

public class OrNode extends BinOpNode {

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }

}
