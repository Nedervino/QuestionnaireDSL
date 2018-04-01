package ql.ast;

public abstract class ASTNode {

    //TODO: accept ParserRuleContext as constructor argument?

    private final SourceLocation sourceLocation;

    protected ASTNode(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public SourceLocation getSourceLocation() {
        return sourceLocation;
    }

}
