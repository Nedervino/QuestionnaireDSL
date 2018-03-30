package ql.gui.widgets;

import ql.gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.BooleanValue;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private final JCheckBox checkBox;

    public CheckboxWidget(FormEvaluator evaluator, Question question, boolean isEditable) {
        super(evaluator, question, isEditable);
        checkBox = new JCheckBox();
        checkBox.setEnabled(isEditable);
    }

    @Override
    public void setValue() {

    }

    @Override
    public void setVisible(boolean visible) {
        checkBox.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
            checkBox.addActionListener(e -> widgetListener.onQuestionUpdated(question, new BooleanValue(checkBox.isSelected())));
    }

}
