package qls.gui;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.gui.WidgetFactory;
import ql.gui.widgets.Widget;

public class QLSWidgetFactory extends WidgetFactory {

    @Override
    public Widget createWidget(Question question, FormEvaluator evaluator) {
        return super.createWidget(question, evaluator);
    }
}
