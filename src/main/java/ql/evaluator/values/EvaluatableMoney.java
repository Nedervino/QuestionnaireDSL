package ql.evaluator.values;

import java.math.BigDecimal;

public class EvaluatableMoney extends EvaluatableNumeric<BigDecimal> {

    private BigDecimal value;

    public EvaluatableMoney(BigDecimal value) {
        this.value = value;
        round();
    }

    public EvaluatableMoney(double value) {
        this(new BigDecimal(value));
    }

    public void round(){
        value = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Evaluatable add(EvaluatableMoney evaluatable) {
        return new EvaluatableMoney(getValue().add(evaluatable.getValue()));
    }

    @Override
    public Evaluatable divide(EvaluatableMoney evaluatable) {
        return new EvaluatableDecimal(evaluatable.getValue().doubleValue() / getValue().doubleValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableDecimal evaluatable) {
        return new EvaluatableMoney(getValue().doubleValue() * evaluatable.getValue());
    }

    @Override
    public Evaluatable multiply(EvaluatableInteger evaluatable) {
        return new EvaluatableMoney(getValue().doubleValue() * (double) evaluatable.getValue());
    }

    @Override
    public EvaluatableBoolean equal(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(getValue().equals(evaluatable.getValue()));
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(getValue().compareTo(evaluatable.getValue()) >= 0);
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(getValue().compareTo(evaluatable.getValue()) > 0);
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(getValue().compareTo(evaluatable.getValue()) <= 0);
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(getValue().compareTo(evaluatable.getValue()) < 0);
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableMoney evaluatable) {
        return new EvaluatableBoolean(!getValue().equals(evaluatable.getValue()));
    }

    @Override
    public Evaluatable subtract(EvaluatableMoney evaluatable) {
        //terms are flipped back since we flip the terms in the double dispacht in subtract(Evaluatable evaluatable)
        return new EvaluatableMoney(evaluatable.getValue().subtract(getValue()));
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        return new EvaluatableMoney(getValue().negate());
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
