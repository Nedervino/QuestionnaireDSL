package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.DecimalValue;
import ql.evaluator.values.IntegerValue;

import javax.swing.*;
import java.awt.*;

public class SliderWidget extends BaseWidget {

    private JSlider slider;

    public SliderWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);

        // IntegerValue value = (IntegerValue) evaluator.getQuestionValue(question.getId());
        // int CURRENT_VALUE = value.getValue();

        int CURRENT_VALUE = 5;
        int START = CURRENT_VALUE - 5;
        int END = CURRENT_VALUE + 5;

        slider = new JSlider(START, END, CURRENT_VALUE);

        slider.setPreferredSize(new Dimension(100, 50));
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
        //TODO
    }

    @Override
    public JComponent getComponent() {
        return slider;
    }
}
