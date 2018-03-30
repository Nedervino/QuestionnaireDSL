package ql.environment.datastore;

import ql.ast.expressions.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionStore {

    private final Map<String, Expression> expressionMap;

    public ExpressionStore() {
        expressionMap = new HashMap<>();
    }

    public void addExpression(String questionIdentifier, Expression expression) {
        expressionMap.put(questionIdentifier, expression);
    }

    public boolean hasExpression(String questionIdentifier) {
        return expressionMap.containsKey(questionIdentifier);
    }

    public Expression getExpression(String questionIdentifier) {
        return expressionMap.get(questionIdentifier);
    }

}
