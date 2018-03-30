package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.evaluator.FormEvaluator;
import ql.evaluator.values.Value;

import javax.swing.*;

public abstract class BaseWidget implements Widget {

    //TODO: remove evaluator reference from baseWidget (circular)
    protected final FormEvaluator evaluator;
    protected final Question question;
    protected final boolean isEditable;

    protected BaseWidget(FormEvaluator evaluator, Question question, boolean isEditable) {
        this.evaluator = evaluator;
        this.question = question;
        this.isEditable = isEditable;
    }

    @Override
    public Value getValue() {
        return null;
    }

}