package ql.evaluator.values;

public interface Evaluatable<T> {

    T getValue();

    void setValue(T value);

    boolean isTrue();

    Evaluatable add(Evaluatable evaluatable);

    Evaluatable add(EvaluatableMoney evaluatable);

    Evaluatable add(EvaluatableInteger evaluatable);

    Evaluatable add(EvaluatableDecimal evaluatable);

    EvaluatableBoolean and(Evaluatable evaluatable);

    EvaluatableBoolean and(EvaluatableBoolean evaluatable);

    Evaluatable divide(Evaluatable evaluatable);

    Evaluatable divide(EvaluatableMoney evaluatable);

    Evaluatable divide(EvaluatableInteger evaluatable);

    Evaluatable divide(EvaluatableDecimal evaluatable);

    EvaluatableBoolean isEqual(Evaluatable evaluatable);

    EvaluatableBoolean isEqual(EvaluatableMoney evaluatable);

    EvaluatableBoolean isEqual(EvaluatableInteger evaluatable);

    EvaluatableBoolean isEqual(EvaluatableDecimal evaluatable);

    EvaluatableBoolean isEqual(EvaluatableString evaluatable);

    EvaluatableBoolean isEqual(EvaluatableBoolean evaluatable);

    EvaluatableBoolean isEqual(EvaluatableDate evaluatable);

    EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable);

    EvaluatableBoolean greaterThanEqual(EvaluatableMoney evaluatable);

    EvaluatableBoolean greaterThanEqual(EvaluatableDecimal evaluatable);

    EvaluatableBoolean greaterThanEqual(EvaluatableInteger evaluatable);

    EvaluatableBoolean greaterThanEqual(EvaluatableDate evaluatable);

    EvaluatableBoolean greaterThan(Evaluatable evaluatable);

    EvaluatableBoolean greaterThan(EvaluatableMoney evaluatable);

    EvaluatableBoolean greaterThan(EvaluatableDecimal evaluatable);

    EvaluatableBoolean greaterThan(EvaluatableInteger evaluatable);

    EvaluatableBoolean greaterThan(EvaluatableDate evaluatable);

    EvaluatableBoolean lessThanEqual(Evaluatable evaluatable);

    EvaluatableBoolean lessThanEqual(EvaluatableMoney evaluatable);

    EvaluatableBoolean lessThanEqual(EvaluatableDecimal evaluatable);

    EvaluatableBoolean lessThanEqual(EvaluatableInteger evaluatable);

    EvaluatableBoolean lessThanEqual(EvaluatableDate evaluatable);

    EvaluatableBoolean lessThan(Evaluatable evaluatable);

    EvaluatableBoolean lessThan(EvaluatableMoney evaluatable);

    EvaluatableBoolean lessThan(EvaluatableDecimal evaluatable);

    EvaluatableBoolean lessThan(EvaluatableInteger evaluatable);

    EvaluatableBoolean lessThan(EvaluatableDate evaluatable);

    Evaluatable multiply(Evaluatable evaluatable);

    Evaluatable multiply(EvaluatableMoney evaluatable);

    Evaluatable multiply(EvaluatableDecimal evaluatable);

    Evaluatable multiply(EvaluatableInteger evaluatable);

    EvaluatableBoolean notEqual(Evaluatable evaluatable);

    EvaluatableBoolean notEqual(EvaluatableMoney evaluatable);

    EvaluatableBoolean notEqual(EvaluatableDecimal evaluatable);

    EvaluatableBoolean notEqual(EvaluatableInteger evaluatable);

    EvaluatableBoolean notEqual(EvaluatableDate evaluatable);

    EvaluatableBoolean notEqual(EvaluatableString evaluatable);

    EvaluatableBoolean notEqual(EvaluatableBoolean evaluatable);

    EvaluatableBoolean or(Evaluatable evaluatable);

    EvaluatableBoolean or(EvaluatableBoolean evaluatable);

    Evaluatable subtract(Evaluatable evaluatable);

    Evaluatable subtract(EvaluatableMoney evaluatable);

    Evaluatable subtract(EvaluatableDecimal evaluatable);

    Evaluatable subtract(EvaluatableInteger evaluatable);

    EvaluatableBoolean logicalNegate();

    EvaluatableNumeric arithmeticNegate();
}
