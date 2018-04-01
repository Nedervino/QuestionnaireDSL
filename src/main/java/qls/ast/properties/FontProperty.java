package qls.ast.properties;

import ql.ast.SourceLocation;
import qls.ast.visitors.PropertyVisitor;

public class FontProperty extends Property {

    protected FontProperty(SourceLocation sourceLocation) {
        super(sourceLocation);
    }

    @Override
    public <T> T accept(PropertyVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
