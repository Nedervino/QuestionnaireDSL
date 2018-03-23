package gui.widgets;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;

public class QuestionComponent {

    //TODO: Inconsistent with input widgets

    private JPanel panel;

    private final JLabel label;
    private final Widget widget;

    public QuestionComponent(FormEvaluator formEvaluator, Question question) {
        label = new JLabel(question.getLabel());
        widget = new TextFieldWidget(formEvaluator, question);
        panel.add(label);
        panel.add(widget.getComponent());
    }

    public JComponent getComponent() {
        return panel;
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

}
