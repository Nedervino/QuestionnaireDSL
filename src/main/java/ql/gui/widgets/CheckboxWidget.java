package ql.gui.widgets;

import ql.gui.WidgetListener;
import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private final JCheckBox checkBox;

    public CheckboxWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
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

    @Override
    public JComponent getComponent() {
        return checkBox;
    }

}
