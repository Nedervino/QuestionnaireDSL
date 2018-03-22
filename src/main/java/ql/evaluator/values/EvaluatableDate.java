package ql.evaluator.values;

import ql.ast.expressions.literals.DateLiteral;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EvaluatableDate implements Evaluatable<Date> {

    private Date value;

    public EvaluatableDate(Date value) {
        this.value = value;
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public EvaluatableBoolean equal(Evaluatable evaluatable) {
        return evaluatable.equal(this);
    }

    @Override
    public EvaluatableBoolean equal(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(value.equals(evaluatable.getValue()));
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return evaluatable.greaterThanEqual(this);
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().after(getValue()) || evaluatable.getValue().equals(getValue()));
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return evaluatable.greaterThan(this);
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().after(getValue()));
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return evaluatable.lessThanEqual(this);
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().before(getValue()) || evaluatable.getValue().equals(getValue()));
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return evaluatable.lessThan(this);
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(evaluatable.getValue().before(getValue()));
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return evaluatable.notEqual(this);
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableDate evaluatable) {
        return new EvaluatableBoolean(!evaluatable.getValue().equals(getValue()));
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateLiteral.DATE_FORMAT);
        return dateFormat.format(getValue());
    }
}
