package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;
import ql.gui.uicomponents.QuestionStyle;

import javax.swing.*;
import java.awt.*;

public class DropdownWidget extends BaseWidget {

    private final JComboBox<String> dropdown;
    private final String trueLabel;
    private final String falseLabel;

    public DropdownWidget(Environment environment, Question question, boolean isEditable) {
        this(environment, question, isEditable, "Yes", "No");
    }

    public DropdownWidget(Environment environment, Question question, boolean isEditable, String trueLabel, String falseLabel) {
        super(environment, question, isEditable);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;

        dropdown = new JComboBox<>();
        dropdown.addItem(this.trueLabel);
        dropdown.addItem(this.falseLabel);
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        if ((boolean) environment.getQuestionValue(question.getId()).getValue()) {
            dropdown.setSelectedItem(trueLabel);
        } else {
            dropdown.setSelectedItem(falseLabel);
        }
    }

    @Override
    public void setStyle(QuestionStyle style) {
        dropdown.setForeground(style.getColor());
        dropdown.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
        dropdown.setFont(style.getFont());
    }

    @Override
    public Value getValue() {
        return new BooleanValue(dropdown.getSelectedIndex() == 0);
    }

    @Override
    public void setVisible(boolean visible) {
        dropdown.setVisible(visible);
    }

    @Override
    public void setEditable(boolean isEditable) {
        dropdown.setEnabled(isEditable);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        dropdown.addActionListener(e -> {
            if (isEditable)
                widgetListener.onInputValueUpdated(question, getValue());
        });
    }

    @Override
    public JComponent getComponent() {
        return dropdown;
    }
}
