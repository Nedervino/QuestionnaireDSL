package ql.gui.widgets;

import ql.ast.statements.Question;
import ql.environment.Environment;
import ql.environment.values.Value;

public abstract class BaseWidget implements Widget {

    protected final Environment environment;
    protected final Question question;
    protected final boolean isEditable;

    protected BaseWidget(Environment environment, Question question, boolean isEditable) {
        this.environment = environment;
        this.question = question;
        this.isEditable = isEditable;
    }

    @Override
    public Value getValue() {
        return null;
    }

}