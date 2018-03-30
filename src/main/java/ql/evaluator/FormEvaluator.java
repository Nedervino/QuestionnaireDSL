package ql.evaluator;

import ql.ast.statements.Question;
import ql.evaluator.values.Value;

import java.util.List;

public interface FormEvaluator {

    void evaluate();

    List<Question> getQuestions();

    Value getQuestionValue(String questionId);

    boolean questionIsComputed(String questionId);

    void setValue(String questionId, Value value);

    boolean questionIsEnabled(String questionId);
}
