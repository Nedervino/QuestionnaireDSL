package ql.validator;

import ql.ast.Form;
import ql.ast.expressions.Expression;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.ast.visitors.TypeVisitor;
import ql.validator.issuetracker.IssueTracker;

import java.util.List;

/**
 * Checks AST for references to undefined questions, conditions of non-boolean type,
 * and invalid operand/operator type combinations
 */
public class ExpressionChecker implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<Type>, TypeVisitor<Type>{
// public class ExpressionChecker {

    private final IssueTracker issueTracker;
    private SymbolTable symbolTable;

    public ExpressionChecker(IssueTracker issueTracker) {
        this.issueTracker = issueTracker;
    }

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        form.accept(this);
        return !issueTracker.hasErrors();
    }

    public Type checkTypeCompatibility(BinaryOperation binaryOperation) {
        Type leftType = binaryOperation.getLeft().accept(this);
        Type rightType = binaryOperation.getRight().accept(this);

        if (!leftType.isCompatibleWith(rightType) && (!leftType.isOfType("error") || rightType.isOfType("error"))) {
            issueTracker.addError(binaryOperation.getSourceLocation(), "Incompatible types within binary operation");
            return new ErrorType(binaryOperation.getSourceLocation());
        }
        return leftType.isOfType("decimal") ? leftType : rightType;
    }

    private void visitStatements(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    private void visitCondition(IfStatement statement) {
        Type type = statement.getCondition().accept(this);
        if(!type.isOfType("boolean") && !type.isOfType("error")) {
            issueTracker.addError(statement.getSourceLocation(), "Non-boolean conditional");
        }
    }

    private Type verifyType(Type actualType, String expectedType) {
        //If issue logged further down the tree, don't log new error
        if(actualType.isOfType(expectedType) || (expectedType.equals("numeric") && (actualType.isOfType("integer") || actualType.isOfType("decimal"))) || actualType.isOfType("error")) {
            return actualType;
        } else {
            issueTracker.addError(actualType.getSourceLocation(), String.format("Type mismatch. Actual: %s Expected: %s", actualType.toString(), expectedType));
            return new ErrorType(actualType.getSourceLocation());
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
        visitCondition(ifStatement);
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        visitStatements(ifElseStatement.getIfStatements());
        visitStatements(ifElseStatement.getElseStatements());
        visitCondition(ifElseStatement);
        return null;
    }

    @Override
    public Void visit(Question question) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        Type computedType = computedQuestion.getExpression().accept(this);
        if(!computedQuestion.getType().isCompatibleWith(computedType) && !computedType.isOfType("error")) {
            issueTracker.addError(computedQuestion.getSourceLocation(), "Computed question type doesn't match expression type");
        }
        return null;
    }

    @Override
    public Type visit(Addition addition) {
        return verifyType(checkTypeCompatibility(addition), "numeric");
    }

    @Override
    public Type visit(Division division) {
        return verifyType(checkTypeCompatibility(division), "numeric");
    }

    //TODO: Equal should also accept boolean
    @Override
    public Type visit(Equal equal) {
        return verifyType(checkTypeCompatibility(equal), "numeric");
    }

    @Override
    public Type visit(GreaterThanEqual greaterThanEqual) {
        return verifyType(checkTypeCompatibility(greaterThanEqual), "numeric");

    }

    @Override
    public Type visit(GreaterThan greaterThan) {
        return verifyType(checkTypeCompatibility(greaterThan), "numeric");

    }

    @Override
    public Type visit(LessThanEqual lessThanEqual) {
        return verifyType(checkTypeCompatibility(lessThanEqual), "numeric");
    }

    @Override
    public Type visit(LessThan lessThan) {
        return verifyType(checkTypeCompatibility(lessThan), "numeric");

    }

    @Override
    public Type visit(Multiplication multiplication) {
        return verifyType(checkTypeCompatibility(multiplication), "numeric");
    }

    //TODO: can accept both numeric and boolean
    @Override
    public Type visit(NotEqual notEqual) {
        return verifyType(checkTypeCompatibility(notEqual), "numeric");
    }

    @Override
    public Type visit(LogicalOr logicalOr) {
        return verifyType(checkTypeCompatibility(logicalOr), "boolean");
    }

    @Override
    public Type visit(LogicalAnd logicalAnd) {
        return verifyType(checkTypeCompatibility(logicalAnd), "boolean");
    }

    @Override
    public Type visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public Type visit(LogicalNegation logicalNegation) {
        Type actualType = logicalNegation.accept(this);
        return verifyType(actualType, "boolean");
    }

    @Override
    public Type visit(ArithmeticNegation arithmeticNegation) {
        Type actualType = arithmeticNegation.accept(this);
        return verifyType(actualType, "numeric");
    }

    @Override
    public Type visit(StringLiteral stringLiteral) {
        return new StringType(stringLiteral.getSourceLocation());
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral) {
        return new IntegerType(integerLiteral.getSourceLocation());
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral) {
        return new BooleanType(booleanLiteral.getSourceLocation());
    }

    @Override
    public Type visit(DateLiteral dateLiteral) {
        return new DateType(dateLiteral.getSourceLocation());
    }

    @Override
    public Type visit(DecimalLiteral decimalLiteral) {
        return new DecimalType(decimalLiteral.getSourceLocation());
    }

    @Override
    public Type visit(MoneyLiteral moneyLiteral) {
        return new MoneyType(moneyLiteral.getSourceLocation());
    }

    @Override
    public Type visit(BooleanType booleanType) {
        return booleanType;
    }

    @Override
    public Type visit(DecimalType decimalType) {
        return decimalType;
    }

    @Override
    public Type visit(IntegerType integerType) {
        return integerType;
    }

    @Override
    public Type visit(MoneyType moneyType) {
        return moneyType;
    }

    @Override
    public Type visit(StringType stringType) {
        return stringType;
    }

    @Override
    public Type visit(DateType dateType) {
        return dateType;
    }

    @Override
    public Type visit(ErrorType errorType) {
        return errorType;
    }

    @Override
    public Type visit(Variable variable) {
        if(!symbolTable.isDeclared(variable.toString())) {
            issueTracker.addError(variable.getSourceLocation(), "Reference to undefined question");
            return new ErrorType(variable.getSourceLocation());
        }
        return symbolTable.lookup(variable.toString());
    }

}