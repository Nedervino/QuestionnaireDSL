package ql.ast;

public abstract class ASTNode {

    private SourceLocation sourceLocation;

    public ASTNode(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }
    
}
