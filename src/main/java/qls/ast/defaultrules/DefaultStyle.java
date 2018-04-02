package qls.ast.defaultrules;

import ql.ast.SourceLocation;
import ql.ast.types.Type;
import qls.ast.properties.Property;
import qls.ast.widgets.WidgetType;

import java.util.List;

public class DefaultStyle extends DefaultRule {

    private final Type type;
    private final List<Property> styleProperties;
    private final WidgetType widgetType;

    public DefaultStyle(Type type, List<Property> styleProperties, WidgetType widgetType, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.type = type;
        this.styleProperties = styleProperties;
        this.widgetType = widgetType;
    }
}
