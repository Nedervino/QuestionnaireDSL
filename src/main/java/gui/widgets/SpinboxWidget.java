package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class SpinboxWidget extends BaseWidget {

    private JSpinner spinner;

    public SpinboxWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
    }

    @Override
    public void setValue() {
        //
    }

    @Override
    public void setVisible(boolean visible) {
        this.spinner.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {

    }
}
