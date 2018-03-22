package ql.evaluator.values;

import java.util.Date;

public class ValueDate implements Value<Date> {

    private Date value;

    public ValueDate(Date value) {
        this.value = value;
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
    public ValueBoolean equal(ValueDate evaluatable) {
        return new ValueBoolean(value.equals(evaluatable.getValue()));
    }

    @Override
    public ValueBoolean greaterThanEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThanEqual(ValueDate evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(Value value) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueInteger evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean greaterThan(ValueDate evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueInteger evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThanEqual(ValueDate evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(Value value) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueInteger evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean lessThan(ValueDate evaluatable) {
        return null;
    }

    @Override
    public Value multiply(Value value) {
        return null;
    }

    @Override
    public Value multiply(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public Value multiply(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public Value multiply(ValueInteger evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(Value value) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueInteger evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueDate evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueString evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean notEqual(ValueBoolean evaluatable) {
        return null;
    }

    @Override
    public ValueBoolean or(Value value) {
        return null;
    }

    @Override
    public ValueBoolean or(ValueBoolean evaluatable) {
        return null;
    }

    @Override
    public Value subtract(Value value) {
        return null;
    }

    @Override
    public Value subtract(ValueMoney evaluatable) {
        return null;
    }

    @Override
    public Value subtract(ValueDecimal evaluatable) {
        return null;
    }

    @Override
    public Value subtract(ValueInteger evaluatable) {
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
