package qls.ast.properties;

import ql.ast.SourceLocation;
import qls.ast.visitors.PropertyVisitor;

public class ColorProperty extends Property {

    private final String value;

    public ColorProperty(String value, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(PropertyVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
