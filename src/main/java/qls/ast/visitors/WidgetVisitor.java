package qls.ast.visitors;

import qls.ast.widgets.*;

public interface WidgetVisitor<T> {

    T visit(DefaultType widget);

    T visit(CheckboxType widget);

    T visit(DropdownType widget);

    T visit(RadioType widget);

    T visit(SliderType widget);

    T visit(SpinboxType widget);

    T visit(TextFieldType widget);

}
