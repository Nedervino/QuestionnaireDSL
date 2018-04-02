package ql.environment.values;

import org.apache.commons.lang3.math.NumberUtils;

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

    public MoneyValue(String input) {
        value = new BigDecimal(NumberUtils.toDouble(input, 0));
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
        return new MoneyValue(value.getValue().subtract(getValue()));
    }

    @Override
    public Value negative() {
        return new MoneyValue(getValue().negate());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public Value and(Value value) {
        return value.and(this);
    }

    @Override
    public Value divide(Value value) {
        return value.divide(this);
    }

    @Override
    public Value greaterThanEqual(Value value) {
        return value.greaterThanEqual(this);
    }

    @Override
    public Value greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public Value equal(Value value) {
        return value.equal(this);
    }

    @Override
    public Value lessThanEqual(Value value) {
        return value.lessThanEqual(this);
    }

    @Override
    public Value lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public Value notEqual(Value value) {
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
