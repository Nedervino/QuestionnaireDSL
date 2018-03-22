package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private JCheckBox checkBox;

    public CheckboxWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
        checkBox = new JCheckBox();
        checkBox.setEnabled(true);
        checkBox.setVisible(true);
    }

    @Override
    public void setValue() {

    }

    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {

    }

}
