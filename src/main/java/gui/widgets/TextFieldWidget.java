package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.ValueString;

import javax.swing.*;

public class TextFieldWidget extends BaseWidget {

    private JFormattedTextField textField;

    public TextFieldWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
        textField = new JFormattedTextField();
        setValue();
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        textField.addActionListener(e -> widgetListener.updateEnvironment(question, new ValueString(textField.getText())));
    }

    @Override
    public void setVisible(boolean visible) {
        textField.setVisible(visible);
    }

    @Override
    public void setValue() {
        textField.setValue(evaluator.getQuestionValue(question.getId()));
    }

    @Override
    public JComponent getComponent() {
        return textField;
    }
}
