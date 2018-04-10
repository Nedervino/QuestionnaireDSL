package ql.environment.datastore;

import ql.environment.values.BooleanValue;
import ql.environment.values.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueStore {

    private final Map<String, Value> valueMap;

    public ValueStore() {
        valueMap = new HashMap<>();
    }

    /**
     * Sets a new question value and returns whether it was different from the previous stored entry
     *
     * @return <code>true</code> if the updated value differed from the previous value
     * <code>false</code> otherwise
     */
    public boolean setValue(String questionIdentifier, Value value) {
        Value previousValue = valueMap.put(questionIdentifier, value);
        return previousValue == null || !((BooleanValue) value.equal(previousValue)).getValue();
    }

    public Value getValue(String questionIdentifier) {
        return valueMap.get(questionIdentifier);
    }
}
