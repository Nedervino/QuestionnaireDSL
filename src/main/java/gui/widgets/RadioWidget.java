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

    private Map<String, JRadioButton> choiceButtonMap;
    private JPanel panel;

    public RadioWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));

        this.choiceButtonMap = new HashMap<>();

        ButtonGroup buttonGroup = new ButtonGroup();

        String[] test = {"one", "two", "three"};
        for (int i = 0; i < 3; i++) {
            String name = test[i];
            JRadioButton button = new JRadioButton(name);
            button.setActionCommand(name);
            buttonGroup.add(button);
            panel.add(button);
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
                if(button.isSelected()) {
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
