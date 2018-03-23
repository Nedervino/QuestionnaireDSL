package ql.evaluator.values;

public class BooleanValue implements Value<Boolean> {

    //TODO for all: hoe handelen we null values? Catch je nullpointerExceptions ergens
    private final Boolean value;

    public BooleanValue(Boolean value) {
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
    public BooleanValue and(Value value) {
        return value.and(this);
    }

    public BooleanValue and(BooleanValue value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new BooleanValue(left && right);
    }

    @Override
    public BooleanValue equal(Value value) {
        return value.equal(this);
    }

    public BooleanValue equal(BooleanValue value) {
        return new BooleanValue(getValue().booleanValue() == value.getValue().booleanValue());
    }

    @Override
    public BooleanValue notEqual(Value value) {
        return value.notEqual(this);
    }

    public BooleanValue notEqual(BooleanValue value) {
        Boolean left = value.getValue();
        Boolean right = value.getValue();
        return new BooleanValue(left != right);
    }

    @Override
    public BooleanValue or(Value value) {
        return value.or(this);
    }

    public BooleanValue or(BooleanValue value) {
        return new BooleanValue(getValue() || value.getValue());
    }

    @Override
    public BooleanValue negation() {
        return new BooleanValue(!getValue().booleanValue());
    }

}
