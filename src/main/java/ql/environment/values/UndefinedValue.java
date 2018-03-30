package ql.environment.values;

public class UndefinedValue implements Value<Void> {

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public Value add(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value subtract(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value divide(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value multiply(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value equal(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value notEqual(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value greaterThanEqual(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value greaterThan(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value lessThanEqual(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value lessThan(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value or(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value and(Value value) {
        return new UndefinedValue();
    }

    @Override
    public Value negation() {
        return new UndefinedValue();
    }

    @Override
    public Value negative() {
        return new UndefinedValue();
    }

}
