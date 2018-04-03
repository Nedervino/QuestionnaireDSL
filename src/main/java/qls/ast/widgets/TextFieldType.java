package qls.ast.widgets;

import ql.ast.SourceLocation;
import qls.ast.visitors.WidgetTypeVisitor;

public class TextFieldType extends WidgetType {

    public TextFieldType(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public <T> T accept(WidgetTypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
