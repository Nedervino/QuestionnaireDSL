package qls.ast.visitors;

import qls.ast.properties.ColorProperty;
import qls.ast.properties.FontProperty;
import qls.ast.properties.FontSizeProperty;
import qls.ast.properties.WidthProperty;

public interface PropertyVisitor<T> {

    public T visit(ColorProperty property);

    public T visit(FontProperty property);

    public T visit(FontSizeProperty property);

    public T visit(WidthProperty property);

}
