package ql.validator;

import ql.ast.Form;
import ql.ast.SourceLocation;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.expressions.unary.UnaryOperation;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.validator.cycles.DependencyManager;
import ql.validator.cycles.DependencyPair;
import ql.validator.issuetracker.IssueTracker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Checks AST for cyclic dependencies between questions
 */
public class CyclicDependencyChecker implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<List<Variable>> {


    private final IssueTracker issueTracker;
    private final DependencyManager dependencyManager;

    public CyclicDependencyChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
        this.dependencyManager = new DependencyManager();
    }


    public boolean passesTests(Form form) {
        form.accept(this);
        logCircularDependencies();
        return !issueTracker.hasErrors();
    }

    private void logCircularDependencies() {
        for(DependencyPair circularDependency : dependencyManager.getCircularDependencies()) {
            issueTracker.addError(new SourceLocation(0,0), String.format("Variable %s involved in circular dependency", circularDependency.getSource()));
        }
    }

    private void visitStatements(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    private void addDependencies(Question question, List<Variable> variables) {
        System.out.println(question.getId());
        System.out.println(variables);
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
        List<Variable> result = new ArrayList<Variable>();
        Optional.ofNullable(binaryOperation.getLeft().accept(this)).ifPresent(result::addAll);
        Optional.ofNullable(binaryOperation.getRight().accept(this)).ifPresent(result::addAll);
        return result;
    }

    @Override
    public List<Variable> visit(Addition addition) {
        return visitBinaryOperation(addition);
    }

    @Override
    public List<Variable> visit(LogicalAnd logicalAnd) {
        return visitBinaryOperation(logicalAnd);
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
    public List<Variable> visit(LogicalOr logicalOr) {
        return visitBinaryOperation(logicalOr);
    }

    @Override
    public List<Variable> visit(Subtraction subtraction) {
        return visitBinaryOperation(subtraction);
    }

    @Override
    public List<Variable> visit(LogicalNegation logicalNegation) {
        return visitUnaryOperation(logicalNegation);
    }

    @Override
    public List<Variable> visit(ArithmeticNegation arithmeticNegation) {
        return visitUnaryOperation(arithmeticNegation);
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