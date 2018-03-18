package qls.ast.components;

import qls.ast.SourceLocation;
import qls.ast.ASTNode;

public abstract class Component extends ASTNode {

    public Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }
}
