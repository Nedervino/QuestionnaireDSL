package qls.ast.components;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;

public abstract class Component extends ASTNode {

    protected Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }
}
