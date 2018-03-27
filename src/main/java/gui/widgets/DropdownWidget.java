package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.BooleanValue;

import javax.swing.*;

public class DropdownWidget extends BaseWidget {

    private final String TRUE_LABEL = "YES";
    private final String FALSE_LABEL = "NO";
    private final JComboBox<String> dropdown;

    public DropdownWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
        dropdown = new JComboBox<>();
        dropdown.addItem(TRUE_LABEL);
        dropdown.addItem(FALSE_LABEL);
        dropdown.setSelectedItem(FALSE_LABEL);
        setValue();
    }

    @Override
    public void setValue() {
        if (((BooleanValue) evaluator.getQuestionValue(question.getId())).getValue()) {
            dropdown.setSelectedItem(TRUE_LABEL);
        } else {
            dropdown.setSelectedItem(FALSE_LABEL);
        }
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        boolean newValue = dropdown.getSelectedItem().equals(TRUE_LABEL);
        widgetListener.updateEnvironment(question, new BooleanValue(newValue));
    }

    @Override
    public JComponent getComponent() {
        return dropdown;
    }
}
