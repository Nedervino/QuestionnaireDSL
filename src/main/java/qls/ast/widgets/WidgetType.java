package qls.ast.widgets;

import ql.ast.ASTNode;
import ql.ast.SourceLocation;
import qls.ast.visitors.WidgetTypeVisitor;

public abstract class WidgetType extends ASTNode {

    protected WidgetType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    public abstract <T> T accept(WidgetTypeVisitor<T> visitor);
}
