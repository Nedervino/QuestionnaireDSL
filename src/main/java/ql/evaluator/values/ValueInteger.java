package ql.evaluator.values;

import java.util.Objects;

public class ValueInteger extends ValueNumeric<Integer> {
    //make all final
    private final Integer value;

    public ValueInteger(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Value add(ValueInteger value) {
        return new ValueInteger(value.getValue() + getValue());
    }

    @Override
    public Value divide(ValueInteger value) {
        return new ValueInteger(value.getValue() / getValue());
    }

    @Override
    public Value divide(ValueDecimal value) {
        return new ValueDecimal(value.getValue() / getValue());
    }

    @Override
    public Value divide(ValueMoney value) {
        return new ValueMoney(value.getValue().doubleValue() / getValue());
    }

    @Override
    public ValueBoolean equal(ValueInteger value) {
        return new ValueBoolean(Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public ValueBoolean greaterThanEqual(ValueInteger value) {
        return new ValueBoolean(value.getValue() >= getValue());
    }

    @Override
    public ValueBoolean greaterThan(ValueInteger value) {
        return new ValueBoolean(value.getValue() > getValue());
    }

    @Override
    public ValueBoolean lessThanEqual(ValueInteger value) {
        return new ValueBoolean(value.getValue() <= getValue());
    }

    @Override
    public ValueBoolean lessThan(ValueInteger value) {
        return new ValueBoolean(value.getValue() < getValue());
    }

    @Override
    public Value multiply(ValueInteger value) {
        return new ValueInteger(value.getValue() * getValue());
    }

    @Override
    public Value multiply(ValueDecimal value) {
        return new ValueDecimal(value.getValue() * getValue());
    }

    @Override
    public Value multiply(ValueMoney value) {
        return value.multiply(new ValueMoney(getValue()));
    }

    @Override
    public ValueBoolean notEqual(ValueInteger value) {
        return new ValueBoolean(!Objects.equals(value.getValue(), getValue()));
    }

    @Override
    public Value subtract(ValueInteger value) {
        return new ValueInteger(value.getValue() - getValue());
    }

    @Override
    public ValueNumeric negative() {
        return new ValueInteger(-getValue());
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
