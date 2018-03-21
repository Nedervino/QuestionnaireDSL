package ql.evaluator.values;

import java.math.BigDecimal;

public class EvaluatableMoney extends EvaluatableNumeric<BigDecimal> {

    private BigDecimal value;

    public EvaluatableMoney(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Evaluatable add(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        BigDecimal result = getValue().add(otherValue);
        return new EvaluatableMoney(result);
    }

    @Override
    public Evaluatable divide(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        //terms are flipped just like in the subtract implementation
        BigDecimal result = otherValue.divide(getValue());
        return new EvaluatableMoney(result);
    }

    @Override
    public EvaluatableBoolean equal(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        Boolean result = getValue().equals(otherValue);
        return new EvaluatableBoolean(result);
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        boolean result = getValue().compareTo(otherValue) >= 0;
        return new EvaluatableBoolean(result);
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        boolean result = getValue().compareTo(otherValue) > 0;
        return new EvaluatableBoolean(result);
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        boolean result = getValue().compareTo(otherValue) <= 0;
        return new EvaluatableBoolean(result);
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        boolean result = getValue().compareTo(otherValue) < 0;
        return new EvaluatableBoolean(result);
    }

    @Override
    public Evaluatable multiply(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        BigDecimal result = getValue().multiply(otherValue);
        return new EvaluatableMoney(result);
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableMoney evaluatable) {
        BigDecimal otherValue = evaluatable.getValue();
        Boolean result = !getValue().equals(otherValue);
        return new EvaluatableBoolean(result);
    }

    @Override
    public Evaluatable subtract(EvaluatableMoney evaluatable) {
        System.out.println("subtracting money");
        BigDecimal otherValue = evaluatable.getValue();
        //terms are flipped back since we flip the terms in the double dispacht in subtract(Evaluatable evaluatable)
        BigDecimal result = otherValue.subtract(getValue());
        return new EvaluatableMoney(result);
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        BigDecimal result = getValue().negate();
        return new EvaluatableMoney(result);
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
