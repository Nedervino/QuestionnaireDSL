package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.environment.values.Value;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RadioWidget extends BaseWidget {
    //TODO create separate button map for arbitrary types

    private final Map<String, JRadioButton> choiceButtonMap;
    private final JPanel panel;
    private final ButtonGroup buttonGroup;

    public RadioWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));

        this.choiceButtonMap = new HashMap<>();

        buttonGroup = new ButtonGroup();

        String[] defaultOptions = {"true", "false"};
        for (String option : defaultOptions) {
            JRadioButton button = new JRadioButton(option);
            button.setActionCommand(option);
            buttonGroup.add(button);
            choiceButtonMap.put(option, button);
            panel.add(button);
        }
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
    public Value getValue() {
        for (Map.Entry entry : choiceButtonMap.entrySet()) {
            JRadioButton button = (JRadioButton) entry.getValue();
            if (button.isSelected()) {
                return new BooleanValue((String) entry.getKey());
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
