package ql.gui.uicomponents.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;
import ql.gui.uicomponents.QuestionStyle;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RadioWidget extends BaseWidget {

    private final Map<String, JRadioButton> choiceButtonMap;
    private final JPanel panel;
    private final ButtonGroup buttonGroup;

    public RadioWidget(Environment environment, Question question, boolean isEditable) {
        this(environment, question, isEditable, "Yes", "No");
    }

    public RadioWidget(Environment environment, Question question, boolean isEditable, String trueLabel, String falseLabel) {
        super(environment, question, isEditable);
        this.choiceButtonMap = new HashMap<>();

        panel = new JPanel();

        buttonGroup = new ButtonGroup();

        JRadioButton trueButton = new JRadioButton(trueLabel);
        trueButton.setActionCommand(trueLabel);
        buttonGroup.add(trueButton);
        choiceButtonMap.put("true", trueButton);
        panel.add(trueButton);

        JRadioButton falseButton = new JRadioButton(falseLabel);
        falseButton.setActionCommand(falseLabel);
        buttonGroup.add(falseButton);
        choiceButtonMap.put("false", falseButton);
        panel.add(falseButton);

        setValue();
        setEditable(isEditable);
    }

    @Override
    public void setValue() {
        BooleanValue evaluatable = ((BooleanValue) environment.getQuestionValue(question.getId()));
        boolean value = evaluatable != null ? evaluatable.getValue() : false;
        if (value) {
            buttonGroup.setSelected(choiceButtonMap.get("true").getModel(), true);
        } else {
            buttonGroup.setSelected(choiceButtonMap.get("false").getModel(), true);
        }
    }

    @Override
    public void setStyle(QuestionStyle style) {
        for (JRadioButton button : choiceButtonMap.values()) {
            button.setForeground(style.getColor());
            button.setFont(style.getFont());
        }
        panel.setPreferredSize(new Dimension(style.getWidth(), style.getHeight()));
    }

    @Override
    public Value getValue() {
        for (Map.Entry entry : choiceButtonMap.entrySet()) {
            JRadioButton button = (JRadioButton) entry.getValue();
            if (button.isSelected()) {
                return parseValue((String) entry.getKey());
            }
        }
        return new BooleanValue(false);
    }

    public void setEditable(boolean isEditable) {
        for (JRadioButton button : choiceButtonMap.values()) {
            button.setEnabled(isEditable);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        for (JRadioButton button : choiceButtonMap.values()) {
            button.setVisible(visible);
        }
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        for (Map.Entry entry : choiceButtonMap.entrySet()) {
            JRadioButton button = (JRadioButton) entry.getValue();
            button.addActionListener(e -> {
                if (button.isSelected() && isEditable) {
                    widgetListener.onInputValueUpdated(question, getValue());
                }
            });
        }
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
