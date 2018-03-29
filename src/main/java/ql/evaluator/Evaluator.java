package ql.evaluator;

import issuetracker.IssueTracker;
import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;
import ql.evaluator.values.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Evaluator implements FormStatementVisitor<Void>, ExpressionVisitor<Value>, FormEvaluator {

    private final Map<String, Value> questionValues;
    private final IssueTracker issueTracker;
    private Form form;
    private QuestionCollector questionCollector;

    public Evaluator(Form form) {
        this.form = form;
        issueTracker = new IssueTracker();
        questionValues = new HashMap<>();
        questionCollector = new QuestionCollector();
    }

    @Override
    public void evaluate() {
        visit(form);
    }


    @Override
    public void setValue(String questionId, Value value) {
        questionValues.put(questionId, value);
    }

    @Override
    public List<Question> getQuestions() {
        return questionCollector.getQuestions(form);
    }

    @Override
    public Value getQuestionValue(String questionId) {
        return questionValues.get(questionId);
    }

    @Override
    public Void visit(Question node) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        questionValues.put(node.getId(), node.getExpression().accept(this));
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
            visit(node.getIfStatements());
        }

        return null;
    }

    private void visit(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    @Override
    public Void visit(IfElseStatement node) {
        List<Statement> statements;
        if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
            statements = node.getIfStatements();
        } else {
            statements = node.getElseStatements();
        }
        visit(statements);
        return null;
    }

    //TODO: remove, place accept directly in visits
    private Value getLeftValue(BinaryOperation node) {
        return node.getLeft().accept(this);
    }

    private Value getRightValue(BinaryOperation node) {
        return node.getRight().accept(this);
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
            issueTracker.addError(node.getRight(), "Attempted to divide by zero.");
        }
        return null;
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
        return questionValues.get(variable.getName());
    }

    @Override
    public Void visit(Form form) {
        visit(form.getStatements());
        return null;
    }

}
