package ql.gui;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.Value;
import ql.gui.widgets.Widget;

import javax.swing.*;
import java.awt.*;

public class QuestionUI implements WidgetListener {

    //TODO: Inconsistent with input widgets
    //TODO: Move Question / environment field from widget to QuestionUI

    private final JLabel label;
    private final Widget widget;
    private final JPanel panel;
    private final Environment environment;
    private final Question question;

    public QuestionUI(Environment environment, Question question) {
        this.question = question;
        this.environment = environment;
        label = new JLabel(question.getLabel());
        widget = new WidgetFactory().createWidget(question, environment);
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
        return environment.questionIsEnabled(question.getId());
    }

    @Override
    public void onQuestionUpdated(Question question, Value value) {
        environment.setValue(question.getId(), value);
    }
}
