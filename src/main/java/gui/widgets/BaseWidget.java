package gui.widgets;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Value;

import javax.swing.*;

public abstract class BaseWidget implements Widget {

    //TODO: remove evaluator reference from baseWidget (circular)
    protected final FormEvaluator evaluator;
    protected final Question question;

    protected BaseWidget(FormEvaluator evaluator, Question question) {
        this.evaluator = evaluator;
        this.question = question;
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