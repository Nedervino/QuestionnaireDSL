package qls.ast.widget;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.visitors.WidgetVisitor;

public abstract class WidgetType extends ASTNode {

    public WidgetType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    // public abstract <T> T accept(WidgetVisitor<T> visitor); TODO
}
