package qls.ast.components;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.visitors.ComponentVisitor;

public abstract class Component extends ASTNode {

    protected Component(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract <T> T accept(ComponentVisitor<T> visitor);

}
