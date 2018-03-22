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

    public ValueBoolean equal(ValueString evaluatable) {
        return new ValueBoolean(evaluatable.getValue().equals(getValue()));
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return value.notEqual(this);
    }

    public ValueBoolean notEqual(ValueString evaluatable) {
        return new ValueBoolean(evaluatable.getValue().equals(getValue()));
    }

}
