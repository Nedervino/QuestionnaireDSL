package qls.ast.defaultrules;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import qls.ast.widgets.WidgetType;

public class DefaultWidgetRule extends DefaultRule {

    private final Type type;
    private final WidgetType widgetType;

    public DefaultWidgetRule(Type type, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.type = type;
        this.widgetType = widgetType;
    }

    public Type getType() {
        return type;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }
}
