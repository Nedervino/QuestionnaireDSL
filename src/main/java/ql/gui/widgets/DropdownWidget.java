package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;

public class DropdownWidget extends BaseWidget {

    private String trueLabel;
    private String falseLabel;
    private final JComboBox<String> dropdown;

    public DropdownWidget(Environment environment, Question question, boolean isEditable) {
        this(environment, question, isEditable, "Yes", "No");
    }

    public DropdownWidget(Environment environment, Question question, boolean isEditable, String trueLabel, String falseLabel) {
        super(environment, question, isEditable);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;

        dropdown = new JComboBox<>();
        dropdown.addItem(trueLabel);
        dropdown.addItem(trueLabel);
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
