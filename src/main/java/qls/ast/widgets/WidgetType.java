package qls.ast.widgets;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;

public abstract class WidgetType extends ASTNode {

    protected WidgetType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    // public abstract <T> T accept(WidgetVisitor<T> visitor); TODO
}
