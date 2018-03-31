package ql.gui;

import ql.ast.statements.Question;
import ql.environment.values.Value;

public interface WidgetListener {

    void onInputValueUpdated(Question question, Value value);

}
