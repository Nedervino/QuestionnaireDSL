package qls.ast;

import ql.ast.SourceLocation;

public abstract class ASTNode {

    SourceLocation sourceLocation;

    public ASTNode(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    // public abstract <T> T accept(ASTVisitor<? extends T> visitor);

}
