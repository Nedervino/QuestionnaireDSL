package ql.environment;

import ql.ast.statements.Question;
import ql.environment.values.Value;

import java.util.List;

public interface Environment {

    void evaluate();

    List<Question> getQuestions();

    Value getQuestionValue(String questionId);

    boolean questionIsComputed(String questionId);

    void setValue(String questionId, Value value);

    void registerChangeListener(EnvironmentListener environmentListener);

    boolean questionIsVisible(String questionId);
}
