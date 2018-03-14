package ql.evaluator;

public interface Evaluatable<T> {

    T getValue();

    void setValue(T value);

    boolean isTrue();

    Evaluatable add(Evaluatable evaluatable);

    EvaluatableBoolean and(Evaluatable evaluatable);

    Evaluatable divide(Evaluatable evaluatable);

    EvaluatableBoolean isEqual(Evaluatable evaluatable);

    EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable);

    EvaluatableBoolean greaterThan(Evaluatable evaluatable);

    EvaluatableBoolean lessThanEqual(Evaluatable evaluatable);

    EvaluatableBoolean lessThan(Evaluatable evaluatable);

    Evaluatable multiply(Evaluatable evaluatable);

    EvaluatableBoolean notEqual(Evaluatable evaluatable);

    EvaluatableBoolean or(Evaluatable evaluatable);

    Evaluatable subtract(Evaluatable evaluatable);

    EvaluatableBoolean logicalNegate();

    Evaluatable arithmeticNegate();
}
