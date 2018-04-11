package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.Value;
import ql.gui.WidgetListener;
import ql.gui.uicomponents.QuestionStyle;

import javax.swing.*;
import java.awt.*;

public class SpinboxWidget extends BaseWidget {

    private final JSpinner spinner;

    public SpinboxWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
        spinner = new JSpinner();
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        Value<Number> value = environment.getQuestionValue(question.getId());
        spinner.setValue(value.getValue());
    }

    @Override
    public void setStyle(QuestionStyle style) {
        Component spinComponent = spinner.getEditor().getComponent(0);
        spinComponent.setForeground(style.getColor());
        spinner.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
        spinner.setFont(style.getFont());
    }

    @Override
    public Value getValue() {
        return parseValue(spinner.getValue().toString());
    }

    @Override
    public void setVisible(boolean visible) {
        this.spinner.setVisible(visible);
    }

    @Override
    public void setEditable(boolean isEditable) {
        spinner.setEnabled(isEditable);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        spinner.addChangeListener(e -> {
            if (isEditable) {
                widgetListener.onInputValueUpdated(question, getValue());
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return spinner;
    }
}
