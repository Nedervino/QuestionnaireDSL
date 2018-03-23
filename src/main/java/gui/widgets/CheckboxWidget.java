package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private final JCheckBox checkBox;

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
