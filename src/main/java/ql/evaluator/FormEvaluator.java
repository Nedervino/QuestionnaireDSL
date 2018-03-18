package ql.evaluator;

import ql.ast.Form;
import ql.ast.statements.Question;
import ql.evaluator.values.Evaluatable;

import java.util.List;

public interface FormEvaluator {

    public void start(Form form);

    public void evaluate();

    public List<Question> getQuestions();

    public Evaluatable getQuestionValue(String questionId);

    public void setEvaluatable(String questionId, Evaluatable value);


}
