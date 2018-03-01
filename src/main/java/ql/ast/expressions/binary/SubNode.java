package ql.ast.expressions.binary;

import ql.ast.ASTVisitor;

public class SubNode extends BinOpNode {

    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return visitor.visitSub(this);
    }

}
