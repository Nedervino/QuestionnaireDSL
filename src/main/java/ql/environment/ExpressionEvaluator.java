package ql.environment;

import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.visitors.ExpressionVisitor;
import ql.environment.datastore.ValueStore;
import ql.environment.values.*;

public class ExpressionEvaluator implements ExpressionVisitor<Value> {

    private final ValueStore valueStore;

    public ExpressionEvaluator(ValueStore valueStore) {
        this.valueStore = valueStore;
    }

    public Value evaluate(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public Value visit(Addition node) {
        return getLeftValue(node).add(getRightValue(node));
    }

    @Override
    public Value visit(And node) {
        return getLeftValue(node).and(getRightValue(node));
    }

    @Override
    public Value visit(Division node) {
        try {
            return getLeftValue(node).divide(getRightValue(node));
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Value visit(Equal node) {
        return getLeftValue(node).equal(getRightValue(node));
    }

    @Override
    public Value visit(GreaterThanEqual node) {
        return getLeftValue(node).greaterThanEqual(getRightValue(node));
    }

    @Override
    public Value visit(GreaterThan node) {
        return getLeftValue(node).greaterThan(getRightValue(node));
    }

    @Override
    public Value visit(LessThanEqual node) {
        return getLeftValue(node).lessThanEqual(getRightValue(node));
    }

    @Override
    public Value visit(LessThan node) {
        return getLeftValue(node).lessThan(getRightValue(node));
    }

    @Override
    public Value visit(Multiplication node) {
        return getLeftValue(node).multiply(getRightValue(node));
    }

    @Override
    public Value visit(NotEqual node) {
        return getLeftValue(node).notEqual(getRightValue(node));
    }

    @Override
    public Value visit(Or node) {
        return getLeftValue(node).or(getRightValue(node));
    }

    @Override
    public Value visit(Subtraction node) {
        return getLeftValue(node).subtract(getRightValue(node));
    }

    @Override
    public Value visit(Negation node) {
        return node.getExpression().accept(this).negation();
    }

    @Override
    public Value visit(Negative node) {
        return node.getExpression().accept(this).negative();
    }

    @Override
    public Value visit(StringLiteral node) {
        return new StringValue(node.getValue());
    }

    @Override
    public Value visit(IntegerLiteral node) {
        return new IntegerValue(node.getValue());
    }

    @Override
    public Value visit(BooleanLiteral node) {
        return new BooleanValue(node.getValue());
    }

    @Override
    public Value visit(DateLiteral node) {
        return new DateValue(node.getValue());
    }

    @Override
    public Value visit(DecimalLiteral node) {
        return new DecimalValue(node.getValue());
    }

    @Override
    public Value visit(MoneyLiteral node) {
        return new MoneyValue(node.getValue());
    }

    @Override
    public Value visit(Variable variable) {
        return valueStore.getValue(variable.getName());
    }

    private Value getLeftValue(BinaryOperation node) {
        return node.getLeft().accept(this);
    }

    private Value getRightValue(BinaryOperation node) {
        return node.getRight().accept(this);
    }


}
