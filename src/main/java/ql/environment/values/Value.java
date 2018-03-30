package ql.environment.values;

public interface Value<T> {

    T getValue();

    default Value add(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    //refactor: value
    default Value add(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value add(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value subtract(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value divide(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value multiply(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value equal(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(StringValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(BooleanValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue equal(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value notEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(StringValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue notEqual(BooleanValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value greaterThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThanEqual(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThanEqual(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThanEqual(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThanEqual(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value greaterThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThan(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThan(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThan(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue greaterThan(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value lessThanEqual(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThanEqual(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThanEqual(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThanEqual(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThanEqual(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value lessThan(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThan(MoneyValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThan(DecimalValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThan(IntegerValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue lessThan(DateValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value or(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue or(BooleanValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value and(Value value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default BooleanValue and(BooleanValue value) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value negation() {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Value negative() {
        throw new UnsupportedOperationException(getValue().toString());
    }
}
