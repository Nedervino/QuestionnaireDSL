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
        int CURRENT_VALUE = number.intValue();
        int START = 0;
        int END = CURRENT_VALUE + 10;

        slider = new JSlider(START, END, CURRENT_VALUE);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setPreferredSize(new Dimension(200, 50));
        slider.setEnabled(isEditable);
    }

    @Override
    public void setValue() {
        //TODO
    }

    @Override
    public void setVisible(boolean visible) {
        slider.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        slider.addChangeListener(e -> {
            //wait until user has released slider before updating
            if (!slider.getValueIsAdjusting() && isEditable) {
                widgetListener.onQuestionUpdated(question, new DecimalValue(slider.getValue()));
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return slider;
    }
}
