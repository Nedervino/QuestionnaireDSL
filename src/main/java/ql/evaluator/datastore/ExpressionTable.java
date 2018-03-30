package ql.evaluator.datastore;

import ql.ast.expressions.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTable {

    private final Map<String, Expression> expressionMap;

    public ExpressionTable() {
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
