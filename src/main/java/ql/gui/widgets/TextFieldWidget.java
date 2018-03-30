package ql.gui.widgets;

import ql.gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.StringValue;
import ql.evaluator.values.Value;

import javax.swing.*;
import java.awt.*;

public class TextFieldWidget extends BaseWidget {

    private final JFormattedTextField textField;

    public TextFieldWidget(FormEvaluator evaluator, Question question, boolean isEditable) {
        super(evaluator, question, isEditable);
        textField = new JFormattedTextField();
        textField.setPreferredSize(new Dimension(200, 50));
        setValue();
    }

    @Override
    public void setVisible(boolean visible) {
        textField.setVisible(visible);
    }

    @Override
    public void setValue() {
        Value value = evaluator.getQuestionValue(question.getId());
        if (value != null) {
            textField.setValue(value.getValue());
        }
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        textField.addActionListener(e -> widgetListener.onQuestionUpdated(question, new StringValue(textField.getText())));
    }

    @Override
    public JComponent getComponent() {
        return textField;
    }
}
