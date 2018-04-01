package ql.environment.datastore;

import ql.ast.expressions.Expression;
import ql.ast.statements.Question;

import java.util.*;

public class QuestionStore {

    private final Map<String, Question> questionMap;
    private final Map<String, List<Expression>> conditionMap;

    public QuestionStore() {
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
        if(!hasConditionDependency(questionIdentifier)){
            conditionMap.put(questionIdentifier, new LinkedList<>());
        }
        conditionMap.get(questionIdentifier).add(expression);
    }

    public boolean hasConditionDependency(String questionIdentifier) {
        return conditionMap.containsKey(questionIdentifier);
    }

    public List<Expression> getConditionDependencies(String questionIdentifier) {
        return conditionMap.get(questionIdentifier);
    }

}
