package ql.evaluator.values;

import java.math.BigDecimal;

public class ValueMoney extends ValueNumeric<BigDecimal> {

    private BigDecimal value;

    public ValueMoney(BigDecimal value) {
        this.value = value;
        round();
    }

    public ValueMoney(double value) {
        this(new BigDecimal(value));
    }

    public void round() {
        value = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Value add(ValueMoney evaluatable) {
        return new ValueMoney(getValue().add(evaluatable.getValue()));
    }

    @Override
    public Value divide(ValueMoney evaluatable) {
        return new ValueDecimal(evaluatable.getValue().doubleValue() / getValue().doubleValue());
    }

    @Override
    public Value multiply(ValueDecimal evaluatable) {
        return new ValueMoney(getValue().doubleValue() * evaluatable.getValue());
    }

    @Override
    public Value multiply(ValueInteger evaluatable) {
        return new ValueMoney(getValue().doubleValue() * (double) evaluatable.getValue());
    }

    @Override
    public ValueBoolean equal(ValueMoney evaluatable) {
        return new ValueBoolean(getValue().equals(evaluatable.getValue()));
    }

    @Override
    public ValueBoolean greaterThanEqual(ValueMoney evaluatable) {
        return new ValueBoolean(getValue().compareTo(evaluatable.getValue()) >= 0);
    }

    @Override
    public ValueBoolean greaterThan(ValueMoney evaluatable) {
        return new ValueBoolean(getValue().compareTo(evaluatable.getValue()) > 0);
    }

    @Override
    public ValueBoolean lessThanEqual(ValueMoney evaluatable) {
        return new ValueBoolean(getValue().compareTo(evaluatable.getValue()) <= 0);
    }

    @Override
    public ValueBoolean lessThan(ValueMoney evaluatable) {
        return new ValueBoolean(getValue().compareTo(evaluatable.getValue()) < 0);
    }

    @Override
    public ValueBoolean notEqual(ValueMoney evaluatable) {
        return new ValueBoolean(!getValue().equals(evaluatable.getValue()));
    }

    @Override
    public Value subtract(ValueMoney evaluatable) {
        //terms are flipped back since we flip the terms in the double dispacht in subtract(Value evaluatable)
        return new ValueMoney(evaluatable.getValue().subtract(getValue()));
    }

    @Override
    public ValueNumeric negative() {
        return new ValueMoney(getValue().negate());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public ValueBoolean and(Value value) {
        return value.and(this);
    }

    @Override
    public Value divide(Value value) {
        return value.divide(this);
    }

    @Override
    public ValueBoolean greaterThanEqual(Value value) {
        return value.greaterThanEqual(this);
    }

    @Override
    public ValueBoolean greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public ValueBoolean equal(Value value) {
        return value.equal(this);
    }

    @Override
    public ValueBoolean lessThanEqual(Value value) {
        return value.lessThanEqual(this);
    }

    @Override
    public ValueBoolean lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return value.notEqual(this);
    }

    @Override
    public Value multiply(Value value) {
        return value.multiply(this);
    }

    @Override
    public Value subtract(Value value) {
        return value.subtract(this);
    }

}
