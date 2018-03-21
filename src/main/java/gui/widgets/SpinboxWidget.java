package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class SpinboxWidget extends BaseWidget {

    private JSpinner spinner;

    public SpinboxWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
    }

    @Override
    public void setEditable(boolean editable) {

    }

    @Override
    public void setVisible(boolean visible) {

    }
}
