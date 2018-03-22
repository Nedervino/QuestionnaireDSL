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
    default Value add(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueString evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean equal(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueString evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean notEqual(ValueBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThanEqual(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean greaterThan(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThanEqual(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean lessThan(ValueDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean or(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean or(ValueBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean and(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean and(ValueBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueBoolean negation() {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default ValueNumeric negative() {
        throw new UnsupportedOperationException(getValue().toString());
    }
}
