package ql.environment.values;

public class StringValue implements Value<String> {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public Value fromString(String input) {
        return new StringValue(input);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Value equal(Value value) {
        return value.equal(this);
    }

    @Override
    public BooleanValue equal(StringValue value) {
        return new BooleanValue(value.getValue().equals(getValue()));
    }

    @Override
    public Value notEqual(Value value) {
        return value.notEqual(this);
    }

    @Override
    public BooleanValue notEqual(StringValue value) {
        return new BooleanValue(value.getValue().equals(getValue()));
    }

}
