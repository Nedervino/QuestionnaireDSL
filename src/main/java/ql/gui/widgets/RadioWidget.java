package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.BooleanValue;
import ql.gui.WidgetListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RadioWidget extends BaseWidget {
    //TODO

    private final Map<String, JRadioButton> choiceButtonMap;
    private final JPanel panel;
    private final ButtonGroup buttonGroup;

    public RadioWidget(Environment environment, Question question, boolean isEditable) {
        super(environment, question, isEditable);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));

        this.choiceButtonMap = new HashMap<>();

        buttonGroup = new ButtonGroup();

        String[] test = {"true", "false"};
        for (String name : test) {
            JRadioButton button = new JRadioButton(name);
            button.setActionCommand(name);
            buttonGroup.add(button);
            choiceButtonMap.put(name, button);
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

    public void setEditable(boolean isEditable) {
        for(JRadioButton button : choiceButtonMap.values()) {
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
        for (JRadioButton button : choiceButtonMap.values()) {
            button.addActionListener(e -> {
                if (button.isSelected() && isEditable) {
                    widgetListener.onInputValueUpdated(question, new BooleanValue(Boolean.parseBoolean(button.getText())));
                }
            });
        }
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
