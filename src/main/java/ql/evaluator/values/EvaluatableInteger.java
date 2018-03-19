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
    public void setValue(Integer value) {
        this.value = value;
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
    public EvaluatableBoolean isEqual(EvaluatableInteger evaluatable) {
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
}
