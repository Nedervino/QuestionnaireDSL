package ql.evaluator.values;

import java.math.BigDecimal;

public class MoneyValue extends NumericValue<BigDecimal> {

    private BigDecimal value;

    public MoneyValue(BigDecimal value) {
        this.value = value;
        round();
    }

    public MoneyValue(double value) {
        this(new BigDecimal(value));
    }

    private void round() {
        value = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Value add(MoneyValue value) {
        return new MoneyValue(getValue().add(value.getValue()));
    }

    @Override
    public Value divide(MoneyValue value) {
        return new DecimalValue(value.getValue().doubleValue() / getValue().doubleValue());
    }

    @Override
    public Value multiply(DecimalValue value) {
        return new MoneyValue(getValue().doubleValue() * value.getValue());
    }

    @Override
    public Value multiply(IntegerValue value) {
        return new MoneyValue(getValue().doubleValue() * (double) value.getValue());
    }

    @Override
    public BooleanValue equal(MoneyValue value) {
        return new BooleanValue(getValue().equals(value.getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(MoneyValue value) {
        return new BooleanValue(getValue().compareTo(value.getValue()) >= 0);
    }

    @Override
    public BooleanValue greaterThan(MoneyValue value) {
        return new BooleanValue(getValue().compareTo(value.getValue()) > 0);
    }

    @Override
    public BooleanValue lessThanEqual(MoneyValue value) {
        return new BooleanValue(getValue().compareTo(value.getValue()) <= 0);
    }

    @Override
    public BooleanValue lessThan(MoneyValue value) {
        return new BooleanValue(getValue().compareTo(value.getValue()) < 0);
    }

    @Override
    public BooleanValue notEqual(MoneyValue value) {
        return new BooleanValue(!getValue().equals(value.getValue()));
    }

    @Override
    public Value subtract(MoneyValue value) {
        //terms are flipped back since we flip the terms in the double dispacht in subtract(Value value)
        return new MoneyValue(value.getValue().subtract(getValue()));
    }

    @Override
    public NumericValue negative() {
        return new MoneyValue(getValue().negate());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public BooleanValue and(Value value) {
        return value.and(this);
    }

    @Override
    public Value divide(Value value) {
        return value.divide(this);
    }

    @Override
    public BooleanValue greaterThanEqual(Value value) {
        return value.greaterThanEqual(this);
    }

    @Override
    public BooleanValue greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public BooleanValue equal(Value value) {
        return value.equal(this);
    }

    @Override
    public BooleanValue lessThanEqual(Value value) {
        return value.lessThanEqual(this);
    }

    @Override
    public BooleanValue lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public BooleanValue notEqual(Value value) {
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
