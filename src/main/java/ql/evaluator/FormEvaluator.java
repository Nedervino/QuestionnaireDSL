package ql.evaluator;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.values.Value;

import java.util.List;

public interface FormEvaluator {

    void start(Form form);

    void evaluate();

    List<Question> getQuestions();

    Value getQuestionValue(String questionId);

    void setEvaluatable(String questionId, Value value);

}
