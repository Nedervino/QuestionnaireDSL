package ql.evaluator.values;

public class EvaluatableBoolean implements Evaluatable<Boolean> {

    private Boolean value;

    public EvaluatableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public boolean isTrue() {
        return value.booleanValue();
    }

    @Override
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return evaluatable.and(this);
    }

    public EvaluatableBoolean and(EvaluatableBoolean evaluatable) {
        boolean left = value;
        boolean right = evaluatable.getValue();
        return new EvaluatableBoolean(left && right);
    }

    @Override
    public EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        return evaluatable.isEqual(this);
    }

    public EvaluatableBoolean isEqual(EvaluatableBoolean evaluatable) {
        return new EvaluatableBoolean(getValue().booleanValue() == evaluatable.getValue().booleanValue());
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return evaluatable.notEqual(this);
    }

    public EvaluatableBoolean notEqual(EvaluatableBoolean evaluatable) {
        boolean left = value;
        boolean right = evaluatable.getValue();
        return new EvaluatableBoolean(left != right);
    }

    @Override
    public EvaluatableBoolean or(Evaluatable evaluatable) {
        return evaluatable.or(this);
    }

    public EvaluatableBoolean or(EvaluatableBoolean evaluatable) {
        return new EvaluatableBoolean(getValue() || evaluatable.getValue());
    }

    @Override
    public EvaluatableBoolean logicalNegate() {
        return new EvaluatableBoolean(!getValue().booleanValue());
    }

}
