package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;

public class SliderWidget extends BaseWidget {

    private JSlider slider;

    public SliderWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
    }

    @Override
    public void setValue() {
        //TODO
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        //TODO
    }
}
