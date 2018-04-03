package qls.ast.defaultrules;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import qls.ast.properties.Property;
import qls.ast.widgets.WidgetType;

import java.util.List;

public class DefaultStyleRule extends DefaultRule {

    private final Type type;
    private final List<Property> styleProperties;
    private final WidgetType widgetType;

    public DefaultStyleRule(Type type, List<Property> styleProperties, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.type = type;
        this.styleProperties = styleProperties;
        this.widgetType = widgetType;
    }

    public Type getType() {
        return type;
    }

    public List<Property> getStyleProperties() {
        return styleProperties;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }
}
