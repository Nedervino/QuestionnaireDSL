package gui.widgets;

import gui.WidgetListener;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public class TextFieldWidget extends BaseWidget {

    private JFormattedTextField textField;

    public TextFieldWidget(FormEvaluator evaluator, Evaluatable value, String identifier) {
        super(evaluator, value, identifier);

        textField = new JFormattedTextField();

    }

    @Override
    public void addWidgetListener(WidgetListener widgetListener) {

    }

    @Override
    public void setVisible(boolean visible) {
        textField.setVisible(visible);
    }

    @Override
    public JComponent getComponent() {
        return textField;
    }
}
