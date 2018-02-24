package ql.ast;

public class BlockNode extends ASTNode {

    @Override
    public <T> T accept(ASTVisitor<? extends T> visitor) {
        return visitor.visitChildren(this);
    }
}
