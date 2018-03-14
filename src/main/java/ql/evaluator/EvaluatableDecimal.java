package ql.evaluator;

public class EvaluatableDecimal implements EvaluatableNumeric<Double>{

    double value;

    public EvaluatableDecimal(double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Evaluatable add(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public boolean isTrue() {
        return false;
    }

    @Override
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean or(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean logicalNegate() {
        return null;
    }

    @Override
    public Evaluatable arithmeticNegate() {
        return null;
    }
}
