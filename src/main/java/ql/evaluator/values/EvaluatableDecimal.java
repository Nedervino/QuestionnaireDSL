package ql.evaluator.values;

import java.util.Objects;

public class EvaluatableDecimal extends EvaluatableNumeric<Double> {

    private double value;

    public EvaluatableDecimal(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Evaluatable add(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() + getValue());
    }

    @Override
    public Evaluatable divide(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() / getValue());
    }

    @Override
    public EvaluatableBoolean equal(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() >= getValue());
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() > getValue());
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() <= getValue());
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() < getValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() * getValue());
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableDecimal evaluatable) {
        return new EvaluatableBoolean(!Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public Evaluatable subtract(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() - getValue());
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        return new EvaluatableDecimal(-getValue());
    }
}
