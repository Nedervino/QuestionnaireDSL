package ql.environment.values;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class DecimalValue implements Value<Double> {

    private final double value;

    public DecimalValue(double value) {
        this.value = value;
    }

    public DecimalValue(String input) {
        value = NumberUtils.toDouble(input, 0);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Value divide(IntegerValue value) {
        return new DecimalValue(getValue() / value.getValue());
    }

    @Override
    public Value divide(DecimalValue value) {
        return new DecimalValue(getValue() / value.getValue());
    }

    @Override
    public Value divide(Value value) {
        return new DecimalValue(getValue().doubleValue() / ((Number) value.getValue()).doubleValue());
    }

    @Override
    public Value multiply(IntegerValue value) {
        return new DecimalValue(value.getValue() * getValue());
    }

    @Override
    public Value multiply(DecimalValue value) {
        return new DecimalValue(value.getValue() * getValue());
    }

    @Override
    public Value multiply(MoneyValue value) {
        return new MoneyValue(value.getValue().intValue() * getValue());
    }

    @Override
    public Value negative() {
        return new DecimalValue(-getValue());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public Value add(IntegerValue value) {
        return new DecimalValue(value.getValue() + getValue());
    }

    @Override
    public Value add(DecimalValue value) {
        return new DecimalValue(value.getValue() + getValue());
    }

    @Override
    public Value multiply(Value value) {
        return value.multiply(this);
    }

    @Override
    public Value subtract(Value value) {
        return value.negative().add(this);
    }

    @Override
    public Value subtract(IntegerValue value) {
        return new DecimalValue(getValue() - value.getValue());
    }

    @Override
    public Value subtract(DecimalValue value) {
        return new DecimalValue(getValue() - value.getValue());
    }

    @Override
    public Value equal(Value value) {
        return new BooleanValue(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public BooleanValue equal(DecimalValue value) {
        return new BooleanValue(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(DecimalValue value) {
        return new BooleanValue(getValue() >= value.getValue());
    }

    @Override
    public Value greaterThanEqual(Value value) {
        return value.lessThan(this).or(value.equal(this));
    }

    @Override
    public BooleanValue greaterThan(DecimalValue value) {
        return new BooleanValue(getValue() > value.getValue());
    }

    @Override
    public Value greaterThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public BooleanValue lessThanEqual(DecimalValue value) {
        return new BooleanValue(getValue() <= value.getValue());
    }

    @Override
    public Value lessThanEqual(Value value) {
        return value.greaterThan(this).or(value.equal(this));
    }

    @Override
    public BooleanValue lessThan(DecimalValue value) {
        return new BooleanValue(getValue() < value.getValue());
    }

    @Override
    public Value lessThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public BooleanValue notEqual(DecimalValue value) {
        return new BooleanValue(!Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public Value notEqual(Value value) {
        return value.notEqual(this);
    }

}
