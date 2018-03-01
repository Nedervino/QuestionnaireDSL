package ql.ast.expressions.binary;

import ql.ast.ASTVisitor;

public class AddNode extends BinOpNode {

    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return visitor.visitAdd(this);
    }

}
