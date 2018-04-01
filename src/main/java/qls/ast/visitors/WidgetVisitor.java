package qls.ast.visitors;

import qls.ast.widgets.*;

public interface WidgetVisitor<T> {

    T visit(Default widget);

    T visit(Checkbox widget);

    T visit(Dropdown widget);

    T visit(Radio widget);

    T visit(Slider widget);

    T visit(Spinbox widget);

    T visit(TextField widget);

}
