package gui.widgets;

import gui.WidgetListener;
import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Evaluatable;

import javax.swing.*;

public abstract class BaseWidget implements Widget {

    protected final FormEvaluator evaluator;
    protected final Question question;
    // private final Evaluatable value;
    // private final String identifier;

    public BaseWidget(FormEvaluator evaluator, Question question) {
        this.evaluator = evaluator;
        this.question = question;
        // this.value = value;
        // this.identifier = identifier;
    }

    @Override
    public Evaluatable getValue() {
        return null;
    }

    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void setVisible(boolean visible) {

    }

}