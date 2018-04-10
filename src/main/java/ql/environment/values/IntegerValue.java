package ql.environment.values;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class IntegerValue implements Value<Integer> {

    private final Integer value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public IntegerValue(String input) {
        value = NumberUtils.toInt(input, 0);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Value divide(IntegerValue value) {
        return new IntegerValue(getValue() / value.getValue());
    }

    @Override
    public Value divide(DecimalValue value) {
        return new DecimalValue(getValue() / value.getValue());
    }

    @Override
    public Value divide(Value value) {
        return new IntegerValue(getValue().intValue() / ((Number) value.getValue()).intValue());
    }

    @Override
    public Value multiply(IntegerValue value) {
        return new IntegerValue(value.getValue() * getValue());
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
        return new IntegerValue(-getValue());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public Value add(IntegerValue value) {
        return new IntegerValue(value.getValue() + getValue());
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
        return new IntegerValue(getValue() - value.getValue());
    }

    @Override
    public Value equal(Value value) {
        return new BooleanValue(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public BooleanValue equal(IntegerValue value) {
        return new BooleanValue(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(IntegerValue value) {
        return new BooleanValue(getValue() >= value.getValue());
    }

    @Override
    public Value greaterThanEqual(Value value) {
        return value.lessThan(this).or(value.equal(this));
    }

    @Override
    public BooleanValue greaterThan(IntegerValue value) {
        return new BooleanValue(getValue() > value.getValue());
    }

    @Override
    public Value greaterThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public BooleanValue lessThanEqual(IntegerValue value) {
        return new BooleanValue(getValue() <= value.getValue());
    }

    @Override
    public Value lessThanEqual(Value value) {
        return value.greaterThan(this).or(value.equal(this));
    }

    @Override
    public BooleanValue lessThan(IntegerValue value) {
        return new BooleanValue(getValue() < value.getValue());
    }

    @Override
    public Value lessThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public BooleanValue notEqual(IntegerValue value) {
        return new BooleanValue(!Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public Value notEqual(Value value) {
        return value.notEqual(this);
    }

}
