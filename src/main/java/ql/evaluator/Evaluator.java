package ql.evaluator;

import ql.ast.ASTNode;
import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.evaluator.values.*;
import issuetracker.IssueTracker;

import java.util.*;


public class Evaluator implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<Evaluatable>, FormEvaluator {

    private HashMap<ASTNode, Evaluatable> questionValues;
    private HashMap<String, Question> idLookup;
    private Form form;
    private IssueTracker issueTracker;

    public Evaluator(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
        questionValues = new HashMap<>();
        idLookup = new HashMap<>();
    }

    @Override
    public void start(Form form) {
        this.form = form;
        evaluate();
    }

    @Override
    public void setEvaluatable(String questionId, Evaluatable value) {
        Question node = idLookup.get(questionId);
        questionValues.put(node, value);
    }

    @Override
    public void evaluate() {
        try {
            visit(form);
        }
        catch(ArithmeticException e){
            issueTracker.addError(null, "Attempted to divide by zero.");
        }
    }

    @Override
    public List<Question> getQuestions() {
        return new LinkedList(idLookup.values());
    }

    @Override
    public Evaluatable getQuestionValue(String questionId) {
        Question node = idLookup.get(questionId);
        return questionValues.get(node);
    }

    public boolean isCalculated(ASTNode node) {
        return questionValues.containsKey(node);
    }

    private boolean isCalculated(Evaluatable leftEvaluatable, Evaluatable rightEvaluatable) {
        return isCalculated(leftEvaluatable) && isCalculated(rightEvaluatable);
    }

    private boolean isCalculated(Evaluatable evaluatable) {
        return evaluatable != null;
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
        Evaluatable value = expression.accept(this);
        if (isCalculated(value)) {
            questionValues.put(node, value);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement node) {
        Expression expression = node.getCondition();
        Evaluatable value = expression.accept(this);

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
        Evaluatable value = expression.accept(this);
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

    private Evaluatable visitLeft(BinaryOperation node) {
        return node.getLeft().accept(this);
    }

    private Evaluatable visitRight(BinaryOperation node) {
        return node.getRight().accept(this);
    }

    @Override
    public Evaluatable visit(Addition node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        Evaluatable result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.add(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(LogicalAnd node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.and(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(Division node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        Evaluatable result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.divide(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(Equal node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.equal(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(GreaterThanEqual node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.greaterThanEqual(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(GreaterThan node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.greaterThan(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(LessThanEqual node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.lessThanEqual(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(LessThan node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.lessThan(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(Multiplication node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        Evaluatable result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.multiply(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(NotEqual node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.notEqual(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(LogicalOr node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        EvaluatableBoolean result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.or(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(Subtraction node) {
        Evaluatable leftEvaluatable = visitLeft(node);
        Evaluatable rightEvaluatable = visitRight(node);
        Evaluatable result = null;
        if (isCalculated(leftEvaluatable, rightEvaluatable)) {
            result = leftEvaluatable.subtract(rightEvaluatable);
        }
        return result;
    }

    @Override
    public Evaluatable visit(LogicalNegation node) {
        Evaluatable evaluatable = node.getExpression().accept(this);
        EvaluatableBoolean result = null;
        if (isCalculated(evaluatable)) {
            result = evaluatable.logicalNegate();
        }
        return result;
    }

    @Override
    public Evaluatable visit(ArithmeticNegation node) {
        Evaluatable evaluatable = node.getExpression().accept(this);
        Evaluatable result = null;
        if (isCalculated(evaluatable)) {
            result = evaluatable.arithmeticNegate();
        }
        return result;
    }

    @Override
    public Evaluatable visit(StringLiteral node) {
        return new EvaluatableString(node.getValue());
    }

    @Override
    public Evaluatable visit(IntegerLiteral node) {
        return new EvaluatableInteger(node.getValue());
    }

    @Override
    public Evaluatable visit(BooleanLiteral node) {
        return new EvaluatableBoolean(node.getValue());
    }

    @Override
    public Evaluatable visit(DateLiteral node) {
        return new EvaluatableDate(node.getValue());
    }

    @Override
    public Evaluatable visit(DecimalLiteral node) {
        return new EvaluatableDecimal(node.getValue());
    }

    @Override
    public Evaluatable visit(MoneyLiteral node) {
        return new EvaluatableMoney(node.getValue());
    }

    @Override
    public Evaluatable visit(Variable variable) {
        String varName = variable.toString();
        Question declarationNode = findDeclarationNode(varName);
        Evaluatable value = null;
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
