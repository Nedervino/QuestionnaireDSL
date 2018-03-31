package ql.environment.values;

import ql.ast.expressions.literals.DateLiteral;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue implements Value<Date> {

    private final Date value;

    public DateValue(Date value) {
        this.value = value;
    }

    public DateValue(String input) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DateLiteral.getDateFormat());
            value = dateFormat.parse(input);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public String getDisplayValue() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateLiteral.getDateFormat());
        return dateFormat.format(getValue());
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public Value equal(Value value) {
        return value.equal(this);
    }

    @Override
    public BooleanValue equal(DateValue value) {
        return new BooleanValue(getValue().equals(value.getValue()));
    }

    @Override
    public Value greaterThanEqual(Value value) {
        return value.greaterThanEqual(this);
    }

    @Override
    public BooleanValue greaterThanEqual(DateValue value) {
        return new BooleanValue(value.getValue().after(getValue()) || value.getValue().equals(getValue()));
    }

    @Override
    public Value greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public BooleanValue greaterThan(DateValue value) {
        return new BooleanValue(value.getValue().after(getValue()));
    }

    @Override
    public Value lessThanEqual(Value value) {
        return value.lessThanEqual(this);
    }

    @Override
    public BooleanValue lessThanEqual(DateValue value) {
        return new BooleanValue(value.getValue().before(getValue()) || value.getValue().equals(getValue()));
    }

    @Override
    public Value lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public BooleanValue lessThan(DateValue value) {
        return new BooleanValue(value.getValue().before(getValue()));
    }

    @Override
    public Value notEqual(Value value) {
        return value.notEqual(this);
    }

    @Override
    public BooleanValue notEqual(DateValue value) {
        return new BooleanValue(!getValue().equals(value.getValue()));
    }

}
