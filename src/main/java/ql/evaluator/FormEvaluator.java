package ql.evaluator;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.values.Evaluatable;

import java.util.List;

public interface FormEvaluator {

    void start(Form form);

    void evaluate();

    List<Question> getQuestions();

    Evaluatable getQuestionValue(String questionId);

    void setEvaluatable(String questionId, Evaluatable value);

}
