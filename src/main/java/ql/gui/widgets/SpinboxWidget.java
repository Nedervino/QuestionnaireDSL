package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.IntegerValue;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;

public class SpinboxWidget extends BaseWidget {

    private final JSpinner spinner;

    public SpinboxWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);
        // String[] choices = {"1", "2", "3", "4"};
        // spinner = new JSpinner(new SpinnerListModel(choices));
        spinner = new JSpinner();
        spinner.setPreferredSize(new Dimension(200, 50));
        spinner.setEnabled(isEditable);
        setValue();
    }

    @Override
    public void setValue() {
        IntegerValue value = (IntegerValue) environment.getQuestionValue(question.getId());
        spinner.setValue(Integer.valueOf(value.getValue()));
    }

    @Override
    public void setVisible(boolean visible) {
        this.spinner.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        spinner.addChangeListener(e -> {
            IntegerValue integerValue = new IntegerValue((int) spinner.getValue());
            widgetListener.onQuestionUpdated(question, integerValue);
        });
    }

    @Override
    public JComponent getComponent() {
        return spinner;
    }
}
