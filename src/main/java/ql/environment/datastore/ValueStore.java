package ql.environment.datastore;

import ql.environment.values.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueStore {

    private final Map<String, Value> valueMap;

    public ValueStore() {
        valueMap = new HashMap<>();
    }

    public void setValue(String questionIdentifier, Value value) {
        valueMap.put(questionIdentifier, value);
    }

    public Value getValue(String questionIdentifier) {
        return valueMap.get(questionIdentifier);
    }
}
