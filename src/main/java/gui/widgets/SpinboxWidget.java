package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;

import javax.swing.*;
import java.awt.*;

public class SpinboxWidget extends BaseWidget {

    private JSpinner spinner;

    public SpinboxWidget(FormEvaluator evaluator, Question question) {
        super(evaluator, question);
        String[] choices = {"1", "2", "3", "4"};
        spinner = new JSpinner(new SpinnerListModel(choices));
        spinner.setPreferredSize(new Dimension(200, 50));
    }

    @Override
    public void setValue() {
        //
    }

    @Override
    public void setVisible(boolean visible) {
        this.spinner.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {

    }

    @Override
    public JComponent getComponent() {
        return spinner;
    }
}
