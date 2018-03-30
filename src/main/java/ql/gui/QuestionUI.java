package ql.gui;

import ql.evaluator.values.Value;
import ql.gui.widgets.Widget;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;
import java.awt.*;

public class QuestionUI implements WidgetListener {

    //TODO: Inconsistent with input widgets
    //TODO: Move Question / formEvaluator field from widget to QuestionUI

    private final JLabel label;
    private final Widget widget;
    private final JPanel panel;
    private final FormEvaluator formEvaluator;
    private final Question question;

    public QuestionUI(FormEvaluator formEvaluator, Question question) {
        this.question = question;
        this.formEvaluator = formEvaluator;
        label = new JLabel(question.getLabel());
        widget = new WidgetFactory().createWidget(question, formEvaluator);
        widget.registerChangeListener(this);

        panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.add(widget.getComponent(), BorderLayout.EAST);

        panel.setPreferredSize(new Dimension(600, 50));

        setVisible(isEnabled());
    }

    public void update() {
        widget.setValue();
    }

    public JComponent getComponent() {
        return panel;
    }

    public void setVisible(boolean visible) {
        label.setVisible(visible);
        widget.setVisible(visible);
    }

    public boolean isEnabled() {
        return formEvaluator.questionIsEnabled(question.getId());
    }

    @Override
    public void onQuestionUpdated(Question question, Value value) {
        formEvaluator.setValue(question.getId(), value);
    }
}
