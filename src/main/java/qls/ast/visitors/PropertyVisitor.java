package qls.ast.visitors;

import qls.ast.properties.*;

public interface PropertyVisitor<T> {

    T visit(ColorProperty property);

    T visit(FontProperty property);

    T visit(FontSizeProperty property);

    T visit(WidthProperty property);

    T visit(HeightProperty property);
}
