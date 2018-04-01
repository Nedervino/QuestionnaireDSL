package qls.ast.properties;

import ql.ast.SourceLocation;
import qls.ast.visitors.PropertyVisitor;

public class ColorProperty extends Property {

    protected ColorProperty(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public <T> T accept(PropertyVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
