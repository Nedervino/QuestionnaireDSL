package qls.ast;

public abstract class ASTNode {

    private final SourceLocation sourceLocation;

    protected ASTNode(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

    // public abstract <T> T accept(ASTVisitor<? extends T> visitor);

}
