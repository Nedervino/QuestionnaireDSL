package ql.evaluator.values;

public interface Value<T> {

    T getValue();

    //TODO: should be removed from interface, code smell to include for all types
    default boolean getBooleanValue() {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    //refactor: value
    default Value add(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueString value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueBoolean value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueString value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueBoolean value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueMoney value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueDecimal value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueInteger value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueDate value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean or(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean or(ValueBoolean value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean and(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean and(ValueBoolean value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean negation() {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueNumeric negative() {
        throw new UnsupportedOperationException(getValue().toString());
    }
}
