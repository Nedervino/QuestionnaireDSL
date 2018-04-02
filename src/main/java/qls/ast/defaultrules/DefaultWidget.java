package qls.ast.defaultrules;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import qls.ast.widgets.WidgetType;

public class DefaultWidget extends DefaultRule {

    private final Type type;
    private final WidgetType widgetType;

    public DefaultWidget(Type type, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.type = type;
        this.widgetType = widgetType;
    }

}
