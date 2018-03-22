package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.EvaluatableBoolean;

import javax.swing.*;

public class DropdownWidget extends BaseWidget {

    private JComboBox<String> dropdown;
    private final String TRUE_LABEL = "YES";
    private final String FALSE_LABEL = "NO";

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
        if(evaluator.getQuestionValue(question.getId()).getBooleanValue()) {
            dropdown.setSelectedItem(TRUE_LABEL);
        } else {
            dropdown.setSelectedItem(FALSE_LABEL);
        }
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        boolean newValue = dropdown.getSelectedItem().equals(TRUE_LABEL) ? true : false;
        widgetListener.updateEnvironment(question, new EvaluatableBoolean(newValue));
    }

    @Override
    public JComponent getComponent() {
        return dropdown;
    }
}
