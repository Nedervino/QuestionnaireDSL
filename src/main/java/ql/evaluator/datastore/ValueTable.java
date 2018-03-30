package ql.evaluator.datastore;

import ql.evaluator.values.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {

    private final Map<String, Value> valueMap;

    public ValueTable() {
        valueMap = new HashMap<>();
    }

    public void setValue(String questionIdentifier, Value value) {
        valueMap.put(questionIdentifier, value);
    }

    public Value getValue(String questionIdentifier) {
        return valueMap.get(questionIdentifier);
    }
}
