package qls.ast.visitors;

import qls.ast.widget.*;

public interface WidgetVisitor<T> {

    public T visit(Default widget);

    public T visit(Checkbox widget);

    public T visit(Dropdown widget);

    public T visit(Radio widget);

    public T visit(Slider widget);

    public T visit(Spinbox widget);

    public T visit(TextField widget);

}
