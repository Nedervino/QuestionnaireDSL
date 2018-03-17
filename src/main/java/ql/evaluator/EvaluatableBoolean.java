package ql.evaluator;

public class EvaluatableBoolean implements Evaluatable<Boolean>{

    Boolean value;

    public EvaluatableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public boolean isTrue() {
        return value.booleanValue();
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
    public EvaluatableBoolean and(Evaluatable evaluatable) {
        return null;
    }

    public EvaluatableBoolean and(EvaluatableBoolean evaluatable) {
        boolean left = value.booleanValue();
        boolean right = evaluatable.getValue().booleanValue();
        EvaluatableBoolean result = new EvaluatableBoolean(left && right);
        return result;
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

    public EvaluatableBoolean isEqual(EvaluatableBoolean evaluatable) {
        return new EvaluatableBoolean(getValue().booleanValue() == evaluatable.getValue().booleanValue());
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
        return evaluatable.notEqual(this);
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

    public EvaluatableBoolean notEqual(EvaluatableBoolean evaluatable) {
        boolean left = value.booleanValue();
        boolean right = evaluatable.getValue().booleanValue();
        EvaluatableBoolean result = new EvaluatableBoolean(left != right);
        return result;
    }

    @Override
    public EvaluatableBoolean or(Evaluatable evaluatable) {
        return evaluatable.or(this);
    }

    public EvaluatableBoolean or(EvaluatableBoolean evaluatable) {
        return new EvaluatableBoolean(getValue().booleanValue() || evaluatable.getValue().booleanValue());
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
        EvaluatableBoolean result = new EvaluatableBoolean(!getValue().booleanValue());
        return result;
    }

    @Override
    public EvaluatableNumeric arithmeticNegate() {
        return null;
    }
}
