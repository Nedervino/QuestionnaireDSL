package ql.evaluator.values;

public class ValueBoolean implements Value<Boolean> {

    //TODO for all: hoe handelen we null values? Catch je nullpointerExceptions ergens
    private Boolean value;

    public ValueBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean getBooleanValue() {
        return value;
    }

    @Override
    public ValueBoolean and(Value value) {
        return value.and(this);
    }

    public ValueBoolean and(ValueBoolean value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new ValueBoolean(left && right);
    }

    @Override
    public ValueBoolean equal(Value value) {
        return value.equal(this);
    }

    public ValueBoolean equal(ValueBoolean value) {
        return new ValueBoolean(getValue().booleanValue() == value.getValue().booleanValue());
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return value.notEqual(this);
    }

    public ValueBoolean notEqual(ValueBoolean value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new ValueBoolean(left != right);
    }

    @Override
    public ValueBoolean or(Value value) {
        return value.or(this);
    }

    public ValueBoolean or(ValueBoolean value) {
        return new ValueBoolean(getValue() || value.getValue());
    }

    @Override
    public ValueBoolean negation() {
        return new ValueBoolean(!getValue().booleanValue());
    }

}
