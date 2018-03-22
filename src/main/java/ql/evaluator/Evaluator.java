package ql.evaluator;

import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negative;
import ql.ast.expressions.unary.Negation;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;
import ql.evaluator.values.*;
import issuetracker.IssueTracker;

import java.util.*;


public class Evaluator implements FormStatementVisitor<Void>, ExpressionVisitor<Value>, FormEvaluator {

    //TODO: questionValues use String identifier, ASTNodes are not necessary anymore. Also allows removal of idLookup
    private Map<ASTNode, Value> questionValues;
    private Map<String, Question> idLookup;
    private Form form;
    private IssueTracker issueTracker;

    public Evaluator() {
        issueTracker = IssueTracker.getIssueTracker();
        questionValues = new HashMap<>();
        idLookup = new HashMap<>();
    }

    @Override
    public void start(Form form) {
        this.form = form;
        evaluate();
    }

    @Override
    public void setValue(String questionId, Value value) {
        Question node = idLookup.get(questionId);
        questionValues.put(node, value);
    }

    @Override
    public void evaluate() {
        try {
            visit(form);
        } catch (ArithmeticException e) {
            issueTracker.addError(null, "Attempted to divide by zero.");
        }
    }

    @Override
    public List<Question> getQuestions() {
        return new ArrayList(idLookup.values());
    }

    @Override
    public Value getQuestionValue(String questionId) {
        Question node = idLookup.get(questionId);
        return questionValues.get(node);
    }

    //TODO: remove all isCalculated methods and add UndefinedValue type
    public boolean isCalculated(ASTNode node) {
        return questionValues.containsKey(node);
    }

    private boolean isCalculated(Value leftValue, Value rightValue) {
        return isCalculated(leftValue) && isCalculated(rightValue);
    }

    private boolean isCalculated(Value value) {
        return value != null;
    }

    @Override
    public Void visit(Question node) {
        String varName = node.getId();
        idLookup.put(varName, node);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion node) {
        String varName = node.getId();
        idLookup.put(varName, node);

        Expression expression = node.getExpression();
        Value value = expression.accept(this);
        if (isCalculated(value)) {
            questionValues.put(node, value);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        Expression expression = node.getCondition();
        Value value = expression.accept(this);

        if (isCalculated(value)) {
            if (value.getBooleanValue()) {
                visit(node.getIfStatements());
            }
        }

        return null;
    }

    void visit(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    @Override
    public Void visit(IfElseStatement node) {
        Expression expression = node.getCondition();
        Value value = expression.accept(this);
        List<Statement> statements;
        if (isCalculated(expression)) {
            if (value.getBooleanValue()) {
                statements = node.getIfStatements();
            } else {
                statements = node.getElseStatements();
            }
            visit(statements);
        }

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
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        Value result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.add(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(And node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.and(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Division node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        Value result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.divide(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Equal node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.equal(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(GreaterThanEqual node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.greaterThanEqual(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(GreaterThan node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.greaterThan(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(LessThanEqual node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.lessThanEqual(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(LessThan node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.lessThan(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Multiplication node) {
        //TODO: for each visit method directly return with a one-liner such as the following:
        // node.getLeft().accept(this).multiply(node.getRight().accept(this));
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        Value result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.multiply(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(NotEqual node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.notEqual(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Or node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        BooleanValue result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.or(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Subtraction node) {
        Value leftValue = visitLeft(node);
        Value rightValue = visitRight(node);
        Value result = null;
        if (isCalculated(leftValue, rightValue)) {
            result = leftValue.subtract(rightValue);
        }
        return result;
    }

    @Override
    public Value visit(Negation node) {
        Value value = node.getExpression().accept(this);
        BooleanValue result = null;
        if (isCalculated(value)) {
            result = value.negation();
        }
        return result;
    }

    @Override
    public Value visit(Negative node) {
        Value value = node.getExpression().accept(this);
        Value result = null;
        if (isCalculated(value)) {
            result = value.negative();
        }
        return result;
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
        String varName = variable.toString();
        Question declarationNode = findDeclarationNode(varName);
        Value value = null;
        if (isCalculated(declarationNode)) {
            value = questionValues.get(declarationNode);
        }
        return value;
    }

    private Question findDeclarationNode(String varName) {
        return idLookup.get(varName);
    }

    @Override
    public Void visit(Form form) {
        List<Statement> statements = form.getStatements();
        visit(statements);
        return null;
    }

}
