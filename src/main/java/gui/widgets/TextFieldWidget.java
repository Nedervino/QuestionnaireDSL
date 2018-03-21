package gui.widgets;

import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class TextFieldWidget extends BaseWidget {

    private JFormattedTextField textField;

    public TextFieldWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);
    }

}
