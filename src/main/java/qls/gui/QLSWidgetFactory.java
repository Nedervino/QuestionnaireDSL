package qls.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.gui.WidgetFactory;
import ql.gui.widgets.Widget;

public class QLSWidgetFactory extends WidgetFactory {

    @Override
    public Widget createWidget(Question question, Environment environment) {
        return super.createWidget(question, environment);
    }
}
