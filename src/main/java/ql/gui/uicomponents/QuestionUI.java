package ql.gui.uicomponents;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.EnvironmentListener;
import ql.environment.values.Value;
import ql.gui.WidgetListener;
import ql.gui.uicomponents.widgets.Widget;

import javax.swing.*;
import java.awt.*;

public class QuestionUI implements WidgetListener, EnvironmentListener {

    private final JLabel label;
    private final Widget widget;
    private final JPanel panel;
    private final Environment environment;
    private final Question question;

    public QuestionUI(Environment environment, Question question, Widget widget) {
        this.question = question;
        this.environment = environment;

        environment.registerChangeListener(this);

        label = new JLabel(question.getLabel());
        this.widget = widget;
        widget.registerChangeListener(this);

        panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(widget.getComponent(), BorderLayout.EAST);

        panel.setPreferredSize(new Dimension(600, 50));

        setVisible(isVisible());
    }

    public JComponent getComponent() {
        return panel;
    }

    private boolean isVisible() {
        return environment.questionIsVisible(question.getId());
    }

    public void setVisible(boolean visible) {
        label.setVisible(visible);
        widget.setVisible(visible);
    }

    public String getQuestionId() {
        return question.getId();
    }

    @Override
    public void onInputValueUpdated(Question question, Value value) {
        environment.setValue(question.getId(), value);
    }

    @Override
    public void onEnvironmentUpdated() {
        setVisible(isVisible());
        widget.setValue();
    }

}
