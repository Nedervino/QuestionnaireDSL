package ql.evaluator.values;

import ql.ast.expressions.literals.DateLiteral;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue implements Value<Date> {

    private Date value;

    public DateValue(Date value) {
        this.value = value;
    }

    @Override
    public String toString(){
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
        return new BooleanValue(value.equals(value.getValue()));
    }

    @Override
    public BooleanValue greaterThanEqual(Value value) {
        return null;
    }

    @Override
    public BooleanValue greaterThanEqual(DateValue value) {
        return null;
    }

    @Override
    public BooleanValue greaterThan(Value value) {
        return null;
    }

    @Override
    public BooleanValue greaterThan(MoneyValue value) {
        return null;
    }

    @Override
    public BooleanValue greaterThan(DecimalValue value) {
        return null;
    }

    @Override
    public BooleanValue greaterThan(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue greaterThan(DateValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThanEqual(Value value) {
        return null;
    }

    @Override
    public BooleanValue lessThanEqual(MoneyValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThanEqual(DecimalValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThanEqual(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThanEqual(DateValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThan(Value value) {
        return null;
    }

    @Override
    public BooleanValue lessThan(MoneyValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThan(DecimalValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThan(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue lessThan(DateValue value) {
        return null;
    }

    @Override
    public Value multiply(Value value) {
        return null;
    }

    @Override
    public Value multiply(MoneyValue value) {
        return null;
    }

    @Override
    public Value multiply(DecimalValue value) {
        return null;
    }

    @Override
    public Value multiply(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(Value value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(MoneyValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(DecimalValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(DateValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(StringValue value) {
        return null;
    }

    @Override
    public BooleanValue notEqual(BooleanValue value) {
        return null;
    }

    @Override
    public BooleanValue or(Value value) {
        return null;
    }

    @Override
    public BooleanValue or(BooleanValue value) {
        return null;
    }

    @Override
    public Value subtract(Value value) {
        return null;
    }

    @Override
    public Value subtract(MoneyValue value) {
        return null;
    }

    @Override
    public Value subtract(DecimalValue value) {
        return null;
    }

    @Override
    public Value subtract(IntegerValue value) {
        return null;
    }

    @Override
    public BooleanValue negation() {
        return null;
    }

    @Override
    public NumericValue negative() {
        return null;
    }
}
