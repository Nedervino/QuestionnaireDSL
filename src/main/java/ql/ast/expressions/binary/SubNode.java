package ql.ast.expressions.binary;

import ql.ast.visitors.ExpressionVisitor;

public class SubNode extends BinOpNode {

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor){
        return visitor.visit(this);
    }
    
}
