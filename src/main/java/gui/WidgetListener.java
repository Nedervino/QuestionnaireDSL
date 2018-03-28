package gui;

import ql.ast.statements.Question;
import ql.evaluator.values.Value;

public interface WidgetListener {

    void onQuestionUpdated(Question question, Value value);

}
