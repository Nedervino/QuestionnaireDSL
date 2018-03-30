package ql.gui.widgets;

import ql.gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.IntegerValue;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import javax.swing.*;
import java.awt.*;

public class SpinboxWidget extends BaseWidget {

    private final JSpinner spinner;

    public SpinboxWidget(FormEvaluator evaluator, Question question, boolean isEditable) {
        super(evaluator, question, isEditable);
        // String[] choices = {"1", "2", "3", "4"};
        // spinner = new JSpinner(new SpinnerListModel(choices));
        spinner = new JSpinner();
        spinner.setPreferredSize(new Dimension(200, 50));
        setValue();
    }

    @Override
    public void setValue() {
        IntegerValue value = (IntegerValue) evaluator.getQuestionValue(question.getId());
        spinner.setValue(Integer.valueOf(value.getValue()));
    }

    @Override
    public void setVisible(boolean visible) {
        this.spinner.setVisible(visible);
    }

    @Override
    public void registerChangeListener(WidgetListener widgetListener) {
        spinner.addChangeListener(e -> {
            IntegerValue integerValue = new IntegerValue((int) spinner.getValue());
            widgetListener.onQuestionUpdated(question, integerValue);
        });
    }

    @Override
    public JComponent getComponent() {
        return spinner;
    }
}
