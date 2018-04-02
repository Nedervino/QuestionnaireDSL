package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.DecimalValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;

public class SliderWidget extends BaseWidget {

    private final JSlider slider;

    public SliderWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);

        Value value = environment.getQuestionValue(question.getId());
        Number number = value != null ? (Number) value.getValue() : 0;
        final int CURRENT_VALUE = number.intValue();
        final int START = 0;
        final int END = CURRENT_VALUE + 10;

        slider = new JSlider(START, END, CURRENT_VALUE);
        slider.setMinorTickSpacing((END - START) / 10);
        slider.setMajorTickSpacing((END - START) / 5);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setPreferredSize(new Dimension(200, 50));
        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        Value value = environment.getQuestionValue(question.getId());
        Number number = value != null ? (Number) value.getValue() : 0;
        slider.setValue(number.intValue());
    }

    @Override
    public Value getValue() {
        return new DecimalValue(slider.getValue());
    }

    @Override
    public void setVisible(boolean visible) {
        slider.setVisible(visible);
    }

    @Override
    public void setEditable(boolean isEditable) {
        slider.setEnabled(isEditable);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        slider.addChangeListener(e -> {
            //wait until user has released slider before updating
            if (!slider.getValueIsAdjusting() && isEditable) {
                widgetListener.onInputValueUpdated(question, getValue());
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return slider;
    }
}
