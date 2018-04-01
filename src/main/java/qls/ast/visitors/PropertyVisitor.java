package qls.ast.visitors;

import qls.ast.properties.ColorProperty;
import qls.ast.properties.FontProperty;
import qls.ast.properties.FontSizeProperty;
import qls.ast.properties.WidthProperty;

public interface PropertyVisitor<T> {

    T visit(ColorProperty property);

    T visit(FontProperty property);

    T visit(FontSizeProperty property);

    T visit(WidthProperty property);

}
