package ql.evaluator.values;

import ql.ast.expressions.literals.DateLiteral;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueDate implements Value<Date> {

    private Date value;

    public ValueDate(Date value) {
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
    public ValueBoolean equal(Value value) {
        return value.equal(this);
    }

    @Override
    public ValueBoolean equal(ValueDate value) {
        return new ValueBoolean(value.equals(value.getValue()));
    }

    @Override
    public ValueBoolean greaterThanEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThanEqual(ValueDate value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(Value value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueMoney value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueDecimal value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueDate value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueMoney value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueDecimal value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueDate value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(Value value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueMoney value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueDecimal value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueDate value) {
        return null;
    }

    @Override
    public Value multiply(Value value) {
        return null;
    }

    @Override
    public Value multiply(ValueMoney value) {
        return null;
    }

    @Override
    public Value multiply(ValueDecimal value) {
        return null;
    }

    @Override
    public Value multiply(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueMoney value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueDecimal value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueDate value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueString value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueBoolean value) {
        return null;
    }

    @Override
    public ValueBoolean or(Value value) {
        return null;
    }

    @Override
    public ValueBoolean or(ValueBoolean value) {
        return null;
    }

    @Override
    public Value subtract(Value value) {
        return null;
    }

    @Override
    public Value subtract(ValueMoney value) {
        return null;
    }

    @Override
    public Value subtract(ValueDecimal value) {
        return null;
    }

    @Override
    public Value subtract(ValueInteger value) {
        return null;
    }

    @Override
    public ValueBoolean negation() {
        return null;
    }

    @Override
    public ValueNumeric negative() {
        return null;
    }
}
