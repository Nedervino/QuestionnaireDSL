package ql.evaluator.values;

import java.util.Objects;

public class EvaluatableInteger extends EvaluatableNumeric<Integer> {

    private Integer value;

    public EvaluatableInteger(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Evaluatable add(EvaluatableInteger evaluatable) {
        return new EvaluatableInteger(evaluatable.getValue() + getValue());
    }

    @Override
    public Evaluatable divide(EvaluatableInteger evaluatable) {
        return new EvaluatableInteger(evaluatable.getValue() / getValue());
    }

    @Override
    public Evaluatable divide(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() / getValue());
    }

    @Override
    public Evaluatable divide(EvaluatableMoney evaluatable) {
        return new EvaluatableMoney(evaluatable.getValue().doubleValue() / getValue());
    }

    @Override
    public EvaluatableBoolean equal(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() >= getValue());
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() > getValue());
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() <= getValue());
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue() < getValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableInteger evaluatable) {
        return new EvaluatableInteger(evaluatable.getValue() * getValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableDecimal evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue() * getValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableMoney evaluatable) {
        return evaluatable.multiply(new EvaluatableMoney(getValue()));
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableInteger evaluatable) {
        return new EvaluatableBoolean(!Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public Evaluatable subtract(EvaluatableInteger evaluatable) {
        return new EvaluatableInteger(evaluatable.getValue() - getValue());
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        return new EvaluatableInteger(-getValue());
    }

    @Override
    public Evaluatable add(Evaluatable evaluatable) {
        return evaluatable.add(this);
    }

    @Override
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return evaluatable.and(this);
    }

    @Override
    public Evaluatable divide(Evaluatable evaluatable) {
        return evaluatable.divide(this);
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return evaluatable.greaterThanEqual(this);
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return evaluatable.greaterThan(this);
    }

    @Override
    public EvaluatableBoolean equal(Evaluatable evaluatable) {
        return evaluatable.equal(this);
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return evaluatable.lessThanEqual(this);
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return evaluatable.lessThan(this);
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return evaluatable.notEqual(this);
    }

    @Override
    public Evaluatable multiply(Evaluatable evaluatable) {
        return evaluatable.multiply(this);
    }

    @Override
    public Evaluatable subtract(Evaluatable evaluatable) {
        return evaluatable.subtract(this);
    }

}
