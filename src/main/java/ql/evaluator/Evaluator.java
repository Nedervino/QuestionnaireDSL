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
import ql.evaluator.datastore.ExpressionStore;
import ql.evaluator.datastore.QuestionStore;
import ql.evaluator.datastore.ValueStore;
import ql.evaluator.values.*;

import java.util.List;


public class Evaluator implements FormStatementVisitor<String>, ExpressionVisitor<Value>, FormEvaluator {

    //TODO: Split up ExpressionEvaluator to own class

    private final ExpressionStore expressionStore;
    private final QuestionStore questionStore;
    private final ValueStore valueStore;

    private final IssueTracker issueTracker;
    private final QuestionCollector questionCollector;
    private Form form;

    public Evaluator(Form form) {
        this.form = form;

        expressionStore = new ExpressionStore();
        questionStore = new QuestionStore();
        valueStore = new ValueStore();

        issueTracker = new IssueTracker();
        questionCollector = new QuestionCollector();

        visit(form);
    }

    @Override
    public void evaluate() {
        for (Question question : getQuestions()) {
            if (expressionStore.hasExpression(question.getId())) {
                //TODO: replace with static expressionevaluator
                Value value = expressionStore.getExpression(question.getId()).accept(this);
                valueStore.setValue(question.getId(), value);
            }
        }
    }


    @Override
    public void setValue(String questionId, Value value) {
        System.out.printf("Updating. Value for %s was %s\n", questionId, valueStore.getValue(questionId).getValue().toString());
        valueStore.setValue(questionId, value);
        System.out.printf("Value for %s is now %s\n", questionId, valueStore.getValue(questionId).getValue().toString());

    }

    @Override
    public List<Question> getQuestions() {
        return questionStore.getQuestions();
    }

    @Override
    public Value getQuestionValue(String questionId) {
        return valueStore.getValue(questionId);
    }

    @Override
    public boolean questionIsComputed(String questionId) {
        return expressionStore.hasExpression(questionId);
    }

    @Override
    public String visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(Question question) {
        questionStore.addQuestion(question);
        // valueStore.setValue(question.getId(), new StringValue(""));
        return question.getId();
    }

    @Override
    public String visit(ComputedQuestion question) {
        // valueStore.setValue(question.getId(), question.getExpression().accept(this));

        questionStore.addQuestion(question);
        expressionStore.addExpression(question.getId(), question.getExpression());
        return question.getId();
    }

    //TODO: handle nested dependencies within which parent is false but child is true
    @Override
    public String visit(IfStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }

        // if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
        //     visit(node.getIfStatements());
        // }
        return null;
    }

    @Override
    public String visit(IfElseStatement node) {
        for (Statement statement : node.getIfStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, node.getCondition());
            }
        }

        for (Statement statement : node.getElseStatements()) {
            String identifier = statement.accept(this);
            if(identifier != null) {
                questionStore.addConditionDependency(identifier, new Negation(node.getCondition(), node.getSourceLocation()));
            }
        }

        // List<Statement> statements;
        // if (((BooleanValue) node.getCondition().accept(this)).getValue()) {
        //     statements = node.getIfStatements();
        // } else {
        //     statements = node.getElseStatements();
        // }
        // visit(statements);
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
        return valueStore.getValue(variable.getName());
    }

}
