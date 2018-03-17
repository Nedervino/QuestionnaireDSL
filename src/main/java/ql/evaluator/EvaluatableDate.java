package ql.evaluator;

import java.util.Date;

public class EvaluatableDate implements Evaluatable<Date> {

    Date value;

    public EvaluatableDate(Date value) {
        this.value = value;
    }

    @Override
    public Date getValue() {
        return null;
    }

    @Override
    public void setValue(Date value) {

    }

    @Override
    public Evaluatable add(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable add(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public Evaluatable add(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public Evaluatable add(EvaluatableDecimal evaluatable) {
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
    public EvaluatableBoolean and(EvaluatableBoolean evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public Evaluatable divide(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(Evaluatable evaluatable) {
        return evaluatable.isEqual(this);
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableString evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableBoolean evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean isEqual(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThanEqual(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean greaterThan(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThanEqual(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean lessThan(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public Evaluatable multiply(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableDate evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableString evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean notEqual(EvaluatableBoolean evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean or(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean or(EvaluatableBoolean evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(Evaluatable evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(EvaluatableMoney evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(EvaluatableDecimal evaluatable) {
        return null;
    }

    @Override
    public Evaluatable subtract(EvaluatableInteger evaluatable) {
        return null;
    }

    @Override
    public EvaluatableBoolean logicalNegate() {
        return null;
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        return null;
    }
}
