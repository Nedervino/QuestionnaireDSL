package qls.ast.components;

import qls.ast.ASTNode;
import qls.ast.SourceLocation;

public abstract class Component extends ASTNode {

    public Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }
}
