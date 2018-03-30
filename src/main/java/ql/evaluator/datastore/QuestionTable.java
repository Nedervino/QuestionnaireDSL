package ql.evaluator.datastore;

import ql.ast.expressions.Expression;
import ql.ast.statements.Question;

import java.util.*;

public class QuestionTable {

    private final Map<String, Question> questionMap;
    private final Map<String, Expression> conditionMap;

    public QuestionTable() {
        questionMap = new LinkedHashMap<>();
        conditionMap = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questionMap.put(question.getId(), question);
    }

    /**
     * Returns a list of questions in the order by which they were added
     */
    public List<Question> getQuestions() {
        return new ArrayList<>(questionMap.values());
    }

    public void addConditionDependency(String questionIdentifier, Expression expression) {
        conditionMap.put(questionIdentifier, expression);
    }

    public boolean hasConditionDependency(String questionIdentifier) {
        return conditionMap.containsKey(questionIdentifier);
    }

    public Expression getConditionDependency(String questionIdentifier) {
        return conditionMap.get(questionIdentifier);
    }

}
