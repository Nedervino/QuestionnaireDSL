package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;

public class SliderWidget extends BaseWidget {

    private final JSlider slider;

    public SliderWidget(Environment environment, Question question, boolean isEditable, int start, int end, int step) {
        super(environment, question, isEditable);

        Value value = environment.getQuestionValue(question.getId());
        int current = value != null ? ((Number) value.getValue()).intValue() : 0;
        start = current < start ? current : start;
        end = current > end ? current : end;
        slider = new JSlider(start, end, current);
        slider.setMinorTickSpacing(step);
        slider.setMajorTickSpacing(step*2);
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
        return parseValue(Integer.toString(slider.getValue()));
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
