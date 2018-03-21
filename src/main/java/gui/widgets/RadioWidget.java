package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;
import java.util.Map;

public class RadioWidget extends BaseWidget {

    private Map<String, JRadioButton> radioButtons;

    public RadioWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
    }

    @Override
    public void setEditable(boolean editable) {
        // for (JRadioButton button : radioButtons) {
        //
        // }
    }

    @Override
    public void setVisible(boolean visible) {

    }
}
