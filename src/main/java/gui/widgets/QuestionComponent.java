package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class QuestionComponent {

    //TODO: Inconsistent with input widgets

    private JPanel panel;

    private JLabel label;
    private Widget widget;

    public QuestionComponent(FormEvaluator formEvaluator, Question question) {
        label = new JLabel(question.getLabel());
        // widget = new TextFieldWidget(formEvaluator, question);
    }

    public JComponent getComponent() {
        return panel;
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

}
