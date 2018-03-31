package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.gui.WidgetListener;

import javax.swing.*;

public class DropdownWidget extends BaseWidget {

    private final String TRUE_LABEL = "YES";
    private final String FALSE_LABEL = "NO";
    private final JComboBox<String> dropdown;

    public DropdownWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);

        dropdown = new JComboBox<>();
        dropdown.addItem(TRUE_LABEL);
        dropdown.addItem(FALSE_LABEL);
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        if ((boolean) environment.getQuestionValue(question.getId()).getValue()) {
            dropdown.setSelectedItem(TRUE_LABEL);
        } else {
            dropdown.setSelectedItem(FALSE_LABEL);
        }
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
                widgetListener.onInputValueUpdated(question, new BooleanValue(dropdown.getSelectedIndex() == 0));
        });
    }

    @Override
    public JComponent getComponent() {
        return dropdown;
    }
}
