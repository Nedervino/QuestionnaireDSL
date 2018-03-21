package ql.evaluator.values;

public class EvaluatableString implements Evaluatable<String> {

    private String value;

    public EvaluatableString(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public EvaluatableBoolean equal(Evaluatable evaluatable) {
        return evaluatable.equal(this);
    }

    public EvaluatableBoolean equal(EvaluatableString evaluatable) {
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
