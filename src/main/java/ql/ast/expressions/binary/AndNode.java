package ql.ast.expressions.binary;

import ql.ast.ASTVisitor;

public class AndNode extends BinOpNode  {
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return visitor.visitAnd(this);
    }
}
