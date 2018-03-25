package gui;

import gui.widgets.Widget;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;
import java.awt.*;

public class QuestionUI {

    //TODO: Inconsistent with input widgets
    //TODO: Move Question field from widget to QuestionUI

    private final JLabel label;
    private final Widget widget;
    private final JPanel panel;

    public QuestionUI(FormEvaluator formEvaluator, Question question) {
        label = new JLabel(question.getLabel());
        widget = new WidgetFactory().createWidget(question, formEvaluator);
        widget.setVisible(true);

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
