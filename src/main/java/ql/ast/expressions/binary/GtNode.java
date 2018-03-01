package ql.ast.expressions.binary;

import ql.ast.ASTVisitor;

public class GtNode extends BinOpNode {
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return visitor.visitGt(this);
    }
}
