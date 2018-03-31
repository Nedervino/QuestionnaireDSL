package ql.environment.values;

public class BooleanValue implements Value<Boolean> {

    //TODO for all: hoe handelen we null values? Catch je nullpointerExceptions ergens
    private final Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public BooleanValue fromString(String input) {
        if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("true")) {
            return new BooleanValue(true);
        }
        if(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("false")) {
            return new BooleanValue(false);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Value and(Value value) {
        return value.and(this);
    }

    @Override
    public BooleanValue and(BooleanValue value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new BooleanValue(left && right);
    }

    @Override
    public Value equal(Value value) {
        return value.equal(this);
    }

    @Override
    public BooleanValue equal(BooleanValue value) {
        return new BooleanValue(getValue().booleanValue() == value.getValue().booleanValue());
    }

    @Override
    public Value notEqual(Value value) {
        return value.notEqual(this);
    }

    @Override
    public BooleanValue notEqual(BooleanValue value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new BooleanValue(left != right);
    }

    @Override
    public Value or(Value value) {
        return value.or(this);
    }

    @Override
    public BooleanValue or(BooleanValue value) {
        return new BooleanValue(getValue() || value.getValue());
    }

    @Override
    public Value negation() {
        return new BooleanValue(!getValue());
    }

}