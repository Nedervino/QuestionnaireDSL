package ql.evaluator.values;

public class StringValue implements Value<String> {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value value) {
        return value.equal(this);
    }

    public BooleanValue equal(StringValue value) {
        return new BooleanValue(value.getValue().equals(getValue()));
    }

    @Override
    public BooleanValue notEqual(Value value) {
        return value.notEqual(this);
    }

    public BooleanValue notEqual(StringValue value) {
        return new BooleanValue(value.getValue().equals(getValue()));
    }

}
