package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.BooleanValue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RadioWidget extends BaseWidget {

    private final Map<String, JRadioButton> choiceButtonMap;
    private final JPanel panel;
    private final ButtonGroup buttonGroup;

    public RadioWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);

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

        BooleanValue evaluatable = ((BooleanValue) evaluator.getQuestionValue(question.getId()));
        boolean value = evaluatable != null ? evaluatable.getValue() : false;
        if (value) {
            buttonGroup.setSelected(choiceButtonMap.get("true").getModel(), true);
        } else {
            buttonGroup.setSelected(choiceButtonMap.get("false").getModel(), true);
        }

    }

    @Override
    public void setValue() {
        //TODO
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
                if (button.isSelected()) {
                    widgetListener.updateEnvironment(question, new BooleanValue(Boolean.parseBoolean(button.getText())));
                }
            });

        }
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
