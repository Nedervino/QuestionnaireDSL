package ql.environment;

import ql.ast.statements.Question;
import ql.environment.values.Value;

import java.util.List;

public interface Environment {

    void evaluate();

    List<Question> getQuestions();

    Value getQuestionValue(String questionId);

    boolean questionIsComputed(String questionId);

    /**
     * Sets the value in case its question is editable, returns if it was updated
     *
     * @return          <code>true</code> if the question was editable
     *                  <code>false</code> if the question was computed
     */
    boolean setValue(String questionId, Value value);

    void registerChangeListener(EnvironmentListener environmentListener);

    boolean questionIsVisible(String questionId);
}
