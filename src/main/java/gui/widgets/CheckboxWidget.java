package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class CheckboxWidget extends BaseWidget {

    private JCheckBox checkBox;

    public CheckboxWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
        checkBox = new JCheckBox();
        checkBox.setEnabled(true);
        checkBox.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {

    }

}
