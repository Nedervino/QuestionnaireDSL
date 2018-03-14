package ql.evaluator;

import java.math.BigDecimal;

public class EvaluatableMoney implements EvaluatableNumeric<BigDecimal>{

    BigDecimal value;

    public EvaluatableMoney(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Evaluatable add(Evaluatable evaluatable) {
        BigDecimal right = (BigDecimal)evaluatable.getValue();
        BigDecimal result = getValue().add(right);
        System.out.println(result);
        return new EvaluatableMoney(result);
    }

    @Override
    public boolean isTrue() {
        return false;
    }

    @Override
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean or(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(Evaluatable evaluatable) {
        BigDecimal right = (BigDecimal)evaluatable.getValue();
        BigDecimal result = getValue().subtract(right);
        System.out.println(result);
        return new EvaluatableMoney(result);
    }

    @Override
    public EvaluatableBoolean logicalNegate() {
        return null;
    }

    @Override
    public Evaluatable arithmeticNegate() {
        return null;
    }
}
