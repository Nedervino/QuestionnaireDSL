package qls.ast;

import ql.ast.SourceLocation;

public abstract class Component extends ASTNode {

    public Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }
}
