package ql.ast;

public abstract class ASTNode {

    private final SourceLocation sourceLocation;

    public ASTNode(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

}
