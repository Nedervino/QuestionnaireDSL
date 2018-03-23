package gui.widgets;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Value;

import javax.swing.*;

public abstract class BaseWidget implements Widget {

    protected final FormEvaluator evaluator;
    protected final Question question;
    // private final Value value;
    // private final String identifier;

    protected BaseWidget(FormEvaluator evaluator, Question question) {
        this.evaluator = evaluator;
        this.question = question;
        // this.value = value;
        // this.identifier = identifier;
    }

    @Override
    public Value getValue() {
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