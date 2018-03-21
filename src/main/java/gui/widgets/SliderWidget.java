package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class SliderWidget extends BaseWidget {

    private JSlider slider;

    public SliderWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
    }

}
