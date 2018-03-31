package ql.environment.values;

import java.util.Objects;

public class IntegerValue extends NumericValue<Integer> {
    //make all final
    private final Integer value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public IntegerValue(String input) {
        value = Integer.parseInt(input);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Value add(IntegerValue value) {
        return new IntegerValue(value.getValue() + getValue());
    }

    @Override
    public Value divide(IntegerValue value) {
        return new IntegerValue(value.getValue() / getValue());
    }

    @Override
    public Value divide(DecimalValue value) {
        return new DecimalValue(value.getValue() / getValue());
    }

    @Override
    public Value divide(MoneyValue value) {
        return new MoneyValue(value.getValue().doubleValue() / getValue());
    }

    @Override
    public BooleanValue equal(IntegerValue value) {
        return new BooleanValue(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(IntegerValue value) {
        return new BooleanValue(value.getValue() >= getValue());
    }

    @Override
    public BooleanValue greaterThan(IntegerValue value) {
        return new BooleanValue(value.getValue() > getValue());
    }

    @Override
    public BooleanValue lessThanEqual(IntegerValue value) {
        return new BooleanValue(value.getValue() <= getValue());
    }

    @Override
    public BooleanValue lessThan(IntegerValue value) {
        return new BooleanValue(value.getValue() < getValue());
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
        return value.multiply(new MoneyValue(getValue()));
    }

    @Override
    public BooleanValue notEqual(IntegerValue value) {
        return new BooleanValue(!Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public Value subtract(IntegerValue value) {
        return new IntegerValue(value.getValue() - getValue());
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
