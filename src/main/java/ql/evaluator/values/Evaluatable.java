package ql.evaluator.values;

public interface Evaluatable<T> {

    T getValue();

    void setValue(T value);

    boolean isTrue();

    default Evaluatable add(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable add(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable add(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable add(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean and(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean and(EvaluatableBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable divide(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable divide(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable divide(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable divide(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableString evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean isEqual(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThanEqual(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThanEqual(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThanEqual(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThanEqual(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThan(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThan(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThan(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean greaterThan(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThanEqual(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThanEqual(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThanEqual(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThanEqual(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThan(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThan(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThan(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean lessThan(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable multiply(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable multiply(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable multiply(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable multiply(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableDate evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableString evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean notEqual(EvaluatableBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean or(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean or(EvaluatableBoolean evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable subtract(Evaluatable evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable subtract(EvaluatableMoney evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable subtract(EvaluatableDecimal evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default Evaluatable subtract(EvaluatableInteger evaluatable) {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableBoolean logicalNegate() {
        throw new UnsupportedOperationException(getValue().toString());
    }

    default EvaluatableNumeric arithmeticNegate() {
        throw new UnsupportedOperationException(getValue().toString());
    }
}
