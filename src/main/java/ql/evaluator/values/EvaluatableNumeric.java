package ql.evaluator.values;

public abstract class EvaluatableNumeric<T> implements Evaluatable<T> {

    @Override
    public Evaluatable add(Evaluatable evaluatable) {
        return evaluatable.add(this);
    }

    @Override
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return evaluatable.and(this);
    }

    @Override
    public Evaluatable divide(Evaluatable evaluatable) {
        return evaluatable.divide(this);
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return evaluatable.greaterThanEqual(this);
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return evaluatable.greaterThan(this);
    }

    @Override
    public EvaluatableBoolean equal(Evaluatable evaluatable) {
        return evaluatable.equal(this);
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return evaluatable.lessThanEqual(this);
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return evaluatable.lessThan(this);
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return evaluatable.notEqual(this);
    }

    @Override
    public Evaluatable multiply(Evaluatable evaluatable) {
        return evaluatable.multiply(this);
    }

    @Override
    public Evaluatable subtract(Evaluatable evaluatable) {
        return evaluatable.subtract(this);
    }


}
