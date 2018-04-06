package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;

public class SpinboxWidget extends BaseWidget {

    private final JSpinner spinner;

    //TODO
    // public SpinboxWidget(Environment environment, Question question, boolean isEditable, List choices) {
    //     super(environment, question, isEditable);
    //     spinner = new JSpinner(new SpinnerListModel(choices.getItems()));
    // }


    public SpinboxWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
        spinner = new JSpinner();
        spinner.setPreferredSize(new Dimension(200, 50));
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        Value<Number> value = environment.getQuestionValue(question.getId());
        spinner.setValue(value.getValue());
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
