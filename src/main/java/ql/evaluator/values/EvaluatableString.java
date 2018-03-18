package ql.evaluator.values;

public class EvaluatableString implements Evaluatable<String> {

    String value;

    public EvaluatableString(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        return evaluatable.isEqual(this);
    }

    public EvaluatableBoolean isEqual(EvaluatableString evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().equals(getValue()));
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return evaluatable.notEqual(this);
    }

    public EvaluatableBoolean notEqual(EvaluatableString evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().equals(getValue()));
    }

}
