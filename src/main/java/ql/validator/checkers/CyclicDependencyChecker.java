package ql.validator.checkers;

import issuetracker.IssueTracker;
import ql.ast.Form;
import ql.ast.SourceLocation;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.expressions.unary.UnaryOperation;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;
import ql.validator.checkers.cycles.DependencyManager;
import ql.validator.checkers.cycles.DependencyPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Checks AST for cyclic dependencies between questions
 */
public class CyclicDependencyChecker implements Checker, FormStatementVisitor<Void>, ExpressionVisitor<List<Variable>> {


    private final IssueTracker issueTracker;
    private final DependencyManager dependencyManager;

    public CyclicDependencyChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
        this.dependencyManager = new DependencyManager();
    }

    @Override
    public boolean passesTests(Form form) {
        form.accept(this);
        logCircularDependencies();
        return !issueTracker.hasErrors();
    }

    private void logCircularDependencies() {
        for (DependencyPair circularDependency : dependencyManager.getCircularDependencies()) {
            issueTracker.addError(new SourceLocation(0, 0), String.format("Variable %s involved in circular dependency", circularDependency.getSource()));
        }
    }

    private void visitStatements(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    private void addDependencies(Question question, List<Variable> variables) {
        if (variables == null) return;
        for (Variable variable : variables) {
            dependencyManager.addDependency(new DependencyPair(question.getId(), variable.toString()));
        }
    }

    @Override
    public Void visit(Form form) {
        visitStatements(form.getStatements());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        visitStatements(ifStatement.getIfStatements());
        // ifStatement.getCondition().accept(this);
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        visitStatements(ifElseStatement.getIfStatements());
        visitStatements(ifElseStatement.getElseStatements());
        // ifElseStatement.getCondition().accept(this);
        return null;
    }

    @Override
    public Void visit(Question question) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        List<Variable> dependencies = computedQuestion.getExpression().accept(this);
        addDependencies(computedQuestion, dependencies);
        return null;
    }

    public List<Variable> visitUnaryOperation(UnaryOperation unaryOperation) {
        return unaryOperation.getExpression().accept(this);
    }

    public List<Variable> visitBinaryOperation(BinaryOperation binaryOperation) {
        List<Variable> result = new ArrayList<>();
        Optional.ofNullable(binaryOperation.getLeft().accept(this)).ifPresent(result::addAll);
        Optional.ofNullable(binaryOperation.getRight().accept(this)).ifPresent(result::addAll);
        return result;
    }

    @Override
    public List<Variable> visit(Addition addition) {
        return visitBinaryOperation(addition);
    }

    @Override
    public List<Variable> visit(And and) {
        return visitBinaryOperation(and);
    }

    @Override
    public List<Variable> visit(Division division) {
        return visitBinaryOperation(division);

    }

    @Override
    public List<Variable> visit(Equal equal) {
        return visitBinaryOperation(equal);
    }

    @Override
    public List<Variable> visit(GreaterThanEqual greaterThanEqual) {
        return visitBinaryOperation(greaterThanEqual);
    }

    @Override
    public List<Variable> visit(GreaterThan greaterThan) {
        return visitBinaryOperation(greaterThan);
    }

    @Override
    public List<Variable> visit(LessThanEqual lessThanEqual) {
        return visitBinaryOperation(lessThanEqual);
    }

    @Override
    public List<Variable> visit(LessThan lessThan) {
        return visitBinaryOperation(lessThan);
    }

    @Override
    public List<Variable> visit(Multiplication multiplication) {
        return visitBinaryOperation(multiplication);
    }

    @Override
    public List<Variable> visit(NotEqual notEqual) {
        return visitBinaryOperation(notEqual);
    }

    @Override
    public List<Variable> visit(Or or) {
        return visitBinaryOperation(or);
    }

    @Override
    public List<Variable> visit(Subtraction subtraction) {
        return visitBinaryOperation(subtraction);
    }

    @Override
    public List<Variable> visit(Negation negation) {
        return visitUnaryOperation(negation);
    }

    @Override
    public List<Variable> visit(Negative negative) {
        return visitUnaryOperation(negative);
    }

    @Override
    public List<Variable> visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(DecimalLiteral decimalLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(MoneyLiteral moneyLiteral) {
        return null;
    }

    @Override
    public List<Variable> visit(Variable variable) {
        return Collections.singletonList(variable);
    }

}