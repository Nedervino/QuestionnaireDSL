package gui.widgets;

import gui.WidgetListener;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public abstract class BaseWidget implements Widget {

    private final FormEvaluator evaluator;
    private final Evaluatable value;
    private final String identifier;

    public BaseWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        this.evaluator = evaluator;
        this.value = value;
        this.identifier = identifier;
    }

    @Override
    public Evaluatable getValue() {
        return null;
    }

    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void addWidgetListener(WidgetListener widgetListener) {

    }

    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    public void setEditable(boolean editable) {

    }
}