package qls.ast.components;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;

public abstract class Component extends ASTNode {

    public Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }
}
