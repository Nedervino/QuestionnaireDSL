package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.gui.WidgetListener;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private final JCheckBox checkBox;

    public CheckboxWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
        checkBox = new JCheckBox();
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        BooleanValue value = (BooleanValue) environment.getQuestionValue(question.getId()).getValue();
        checkBox.setSelected(value.getValue());
    }

    @Override
    public void setVisible(boolean visible) {
        checkBox.setVisible(visible);
    }

    @Override
    public void setEditable(boolean isEditable) {
        checkBox.setEnabled(isEditable);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        checkBox.addActionListener(e -> {
            if (isEditable) widgetListener.onInputValueUpdated(question, new BooleanValue(checkBox.isSelected()));
        });
    }

    @Override
    public JComponent getComponent() {
        return checkBox;
    }

}
