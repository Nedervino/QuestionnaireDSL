package ql.evaluator;

import issuetracker.IssueTracker;
import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
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

    public Evaluator() {
        issueTracker = IssueTracker.getIssueTracker();
        questionValues = new HashMap<>();
        questionCollector = new QuestionCollector();
    }

    @Override
    public void start(Form form) {
        this.form = form;
        evaluate();
    }

    @Override
    public void setValue(String questionId, Value value) {
        questionValues.put(questionId, value);
    }

    @Override
    public void evaluate() {
        visit(form);
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
        if (node.getCondition().accept(this).getBooleanValue()) {
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
            if (node.getCondition().accept(this).getBooleanValue()) {
                statements = node.getIfStatements();
            } else {
                statements = node.getElseStatements();
            }
            visit(statements);
        return null;
    }

    //TODO: remove, place accept directly in visits
    private Value visitLeft(BinaryOperation node) {
        return node.getLeft().accept(this);
    }

    private Value visitRight(BinaryOperation node) {
        return node.getRight().accept(this);
    }

    @Override
    public Value visit(Addition node) {
        return visitLeft(node).add(visitRight(node));
    }

    @Override
    public Value visit(And node) {
        return visitLeft(node).and(visitRight(node));
    }

    @Override
    public Value visit(Division node) {
        try {
            return visitLeft(node).divide(visitRight(node));
        } catch (ArithmeticException e) {
            issueTracker.addError(node.getRight().getSourceLocation(), "Attempted to divide by zero.");
        }
        return null;
    }

    @Override
    public Value visit(Equal node) {
        return visitLeft(node).equal(visitRight(node));
    }

    @Override
    public Value visit(GreaterThanEqual node) {
        return visitLeft(node).greaterThanEqual(visitRight(node));
    }

    @Override
    public Value visit(GreaterThan node) {
        return visitLeft(node).greaterThan(visitRight(node));
    }

    @Override
    public Value visit(LessThanEqual node) {
        return visitLeft(node).lessThanEqual(visitRight(node));
    }

    @Override
    public Value visit(LessThan node) {
        return visitLeft(node).lessThan(visitRight(node));
    }

    @Override
    public Value visit(Multiplication node) {
        return visitLeft(node).multiply(visitRight(node));
    }

    @Override
    public Value visit(NotEqual node) {
        return visitLeft(node).notEqual(visitRight(node));
    }

    @Override
    public Value visit(Or node) {
        return visitLeft(node).or(visitRight(node));
    }

    @Override
    public Value visit(Subtraction node) {
        return visitLeft(node).subtract(visitRight(node));
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
