package gui;

import gui.widgets.TextFieldWidget;
import gui.widgets.Widget;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;
import java.awt.*;

public class QuestionComponent {

    //TODO: Inconsistent with input widgets
    //TODO: Move Question field from widget to QuestionComponent

    private JPanel panel;

    private final JLabel label;
    private final Widget widget;

    public QuestionComponent(FormEvaluator formEvaluator, Question question) {
        label = new JLabel(question.getLabel());
        widget = new TextFieldWidget(formEvaluator, question);

        panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(widget.getComponent(), BorderLayout.EAST);

        panel.setPreferredSize(new Dimension(600, 50));
    }

    public void update() {
        widget.setValue();
    }

    public JComponent getComponent() {
        return panel;
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

}
