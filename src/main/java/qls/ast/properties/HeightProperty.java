package qls.ast.properties;

import ql.ast.SourceLocation;
import qls.ast.visitors.PropertyVisitor;

public class HeightProperty extends Property {

    private final int value;

    public HeightProperty(int value, SourceLocation sourceLocation) {
        super(sourceLocation);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <T> T accept(PropertyVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
