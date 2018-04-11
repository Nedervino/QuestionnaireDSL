package ql.environment;

import ql.ast.statements.Question;
import ql.environment.values.Value;

import java.util.List;

public interface Environment {

    /**
     * Re-evaluates all expressions belonging to questions, and notifies registered EnvironmentListeners
     */
    void evaluate();

    List<Question> getQuestions();

    Question getQuestion(String questionId);

    Value getQuestionValue(String questionId);

    boolean questionIsComputed(String questionId);

    /**
     * Sets the value in case its question is editable, returns if it was updated
     *
     * @return <code>true</code> if the question was editable
     * <code>false</code> if the question was computed
     */
    boolean setValue(String questionId, Value value);

    void registerChangeListener(EnvironmentListener environmentListener);

    /**
     * Returns whether all parent conditionals evaluate such that the question should be visible
     *
     * @return <code>true</code> if all conditional ancestors for which the question was in the then block are true,
     * and all conditional ancestors for which the question was in the else block are false
     * <code>false</code> otherwise
     */
    boolean questionIsVisible(String questionId);
}
