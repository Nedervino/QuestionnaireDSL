package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;
import ql.gui.uicomponents.QuestionStyle;

import javax.swing.*;
import java.awt.*;

public class CheckboxWidget extends BaseWidget {

    private final JCheckBox checkBox;

    public CheckboxWidget(Environment environment, Question question, boolean isEditable) {
        this(environment, question, isEditable, "Yes");
    }

    public CheckboxWidget(Environment environment, Question question, boolean isEditable, String label) {
        super(environment, question, isEditable);
        checkBox = new JCheckBox();
        checkBox.setText(label);
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setStyle(QuestionStyle style) {
        checkBox.setForeground(style.getColor());
        checkBox.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
        checkBox.setFont(style.getFont());
    }

    @Override
    public void setValue() {
        BooleanValue value = (BooleanValue) environment.getQuestionValue(question.getId());
        checkBox.setSelected(value.getValue());
    }

    @Override
    public Value getValue() {
        return parseValue(Boolean.toString(checkBox.isSelected()));
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
            if (isEditable) widgetListener.onInputValueUpdated(question, getValue());
        });
    }

    @Override
    public JComponent getComponent() {
        return checkBox;
    }

}
