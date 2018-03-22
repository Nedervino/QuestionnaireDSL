package ql.evaluator.values;

import java.util.Objects;

public class ValueDecimal extends ValueNumeric<Double> {

    private double value;

    public ValueDecimal(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Value add(ValueDecimal evaluatable) {
        return new ValueDecimal(evaluatable.getValue() + getValue());
    }

    @Override
    public Value divide(ValueDecimal evaluatable) {
        return new ValueDecimal(evaluatable.getValue() / getValue());
    }

    @Override
    public Value divide(ValueInteger evaluatable) {
        return new ValueMoney(evaluatable.getValue() / getValue());
    }

    @Override
    public Value divide(ValueMoney evaluatable) {
        return new ValueMoney(evaluatable.getValue().doubleValue() / getValue());
    }

    @Override
    public ValueBoolean equal(ValueDecimal evaluatable) {
        return new ValueBoolean(Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public ValueBoolean greaterThanEqual(ValueDecimal evaluatable) {
        return new ValueBoolean(evaluatable.getValue() >= getValue());
    }

    @Override
    public ValueBoolean greaterThan(ValueDecimal evaluatable) {
        return new ValueBoolean(evaluatable.getValue() > getValue());
    }

    @Override
    public ValueBoolean lessThanEqual(ValueDecimal evaluatable) {
        return new ValueBoolean(evaluatable.getValue() <= getValue());
    }

    @Override
    public ValueBoolean lessThan(ValueDecimal evaluatable) {
        return new ValueBoolean(evaluatable.getValue() < getValue());
    }

    @Override
    public Value multiply(ValueDecimal evaluatable) {
        return new ValueDecimal(evaluatable.getValue() * getValue());
    }

    @Override
    public Value multiply(ValueInteger evaluatable) {
        return new ValueDecimal(evaluatable.getValue() * getValue());
    }

    @Override
    public Value multiply(ValueMoney evaluatable) {
        return new ValueMoney(evaluatable.getValue().doubleValue() * getValue());
    }

    @Override
    public ValueBoolean notEqual(ValueDecimal evaluatable) {
        return new ValueBoolean(!Objects.equals(evaluatable.getValue(), getValue()));
    }

    @Override
    public Value subtract(ValueDecimal evaluatable) {
        return new ValueDecimal(evaluatable.getValue() - getValue());
    }

    @Override
    public ValueNumeric negative() {
        return new ValueDecimal(-getValue());
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
