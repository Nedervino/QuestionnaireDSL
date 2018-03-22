package ql.evaluator.values;

public class ValueString implements Value<String> {

    private String value;

    public ValueString(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public ValueBoolean equal(Value value) {
        return value.equal(this);
    }

    public ValueBoolean equal(ValueString value) {
        return new ValueBoolean(value.getValue().equals(getValue()));
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return value.notEqual(this);
    }

    public ValueBoolean notEqual(ValueString value) {
        return new ValueBoolean(value.getValue().equals(getValue()));
    }

}
