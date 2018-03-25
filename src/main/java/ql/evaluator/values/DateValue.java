package ql.evaluator.values;

import ql.ast.expressions.literals.DateLiteral;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue implements Value<Date> {

    private final Date value;

    public DateValue(Date value) {
        this.value = value;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateLiteral.DATE_FORMAT);
        return dateFormat.format(getValue());
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value value) {
        return value.equal(this);
    }

    @Override
    public BooleanValue equal(DateValue value) {
        return new BooleanValue(getValue().equals(value.getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(Value value) {
        return value.greaterThanEqual(this);
    }

    @Override
    public BooleanValue greaterThanEqual(DateValue value) {
        return new BooleanValue(value.getValue().after(getValue()) || value.getValue().equals(getValue()));
    }

    @Override
    public BooleanValue greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public BooleanValue greaterThan(DateValue value) {
        return new BooleanValue(value.getValue().after(getValue()));
    }

    @Override
    public BooleanValue lessThanEqual(Value value) {
        return value.lessThanEqual(this);
    }

    @Override
    public BooleanValue lessThanEqual(DateValue value) {
        return new BooleanValue(value.getValue().before(getValue()) || value.getValue().equals(getValue()));
    }

    @Override
    public BooleanValue lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public BooleanValue lessThan(DateValue value) {
        return new BooleanValue(value.getValue().before(getValue()));
    }

    @Override
    public BooleanValue notEqual(Value value) {
        return value.notEqual(this);
    }

    @Override
    public BooleanValue notEqual(DateValue value) {
        return new BooleanValue(!getValue().equals(value.getValue()));
    }

}
