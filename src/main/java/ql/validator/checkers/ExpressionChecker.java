package ql.validator.checkers;

import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negation;
import ql.ast.expressions.unary.Negative;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormStatementVisitor;
import ql.ast.visitors.TypeVisitor;
import ql.validator.symboltable.SymbolTable;

import java.util.List;

/**
 * Checks AST for references to undefined questions, conditions of non-boolean type,
 * and invalid operand/operator type combinations
 */
public class ExpressionChecker extends BaseChecker implements FormStatementVisitor<Void>, ExpressionVisitor<Type>, TypeVisitor<Type> {

    private SymbolTable symbolTable;

    @Override
    public boolean passesTests(Form form) {
        issueTracker.reset();
        symbolTable = new SymbolTable(form);
        form.accept(this);
        return !issueTracker.hasErrors();
    }

    /**
     * Checks if left and right child are compatible with each other
     *
     * @param binaryOperation
     * @return ErrorType if incompatible, otherwise the dominating return type of the two children
     */
    private Type checkTypeCompatibility(BinaryOperation binaryOperation) {
        Type leftType = binaryOperation.getLeft().accept(this);
        Type rightType = binaryOperation.getRight().accept(this);

        if (!leftType.isCompatibleWith(rightType) && (!leftType.isOfType("error") || rightType.isOfType("error"))) {
            issueTracker.addError(binaryOperation, "Incompatible types within binary operation");
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
        if (!type.isOfType("boolean") && !type.isOfType("error")) {
            issueTracker.addError(statement, "Non-boolean conditional");
        }
    }

    /**
     * Checks if operand type is allowed within parent expression
     *
     * @param actualType
     * @param expectedType
     * @return the actual type if allowed, otherwise ErrorType
     */
    private Type verifyType(Type actualType, String expectedType) {
        //If issue logged further down the tree, don't log new error
        if (actualType.isOfType(expectedType) || (expectedType.equals("numeric") && actualType.isNumeric()) || actualType.isOfType("error")) {
            return actualType;
        } else {
            issueTracker.addError(actualType, String.format("Type mismatch. Actual: %s Expected: %s", actualType.getType(), expectedType));
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
        if (!computedQuestion.getType().isCompatibleWith(computedType) && !computedType.isOfType("error")) {
            issueTracker.addError(computedQuestion, "Computed question type doesn't match expression type");
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
        return visitComparisonExpression(equal);
    }

    @Override
    public Type visit(GreaterThanEqual greaterThanEqual) {
        return visitComparisonExpression(greaterThanEqual);
    }

    @Override
    public Type visit(GreaterThan greaterThan) {
        return visitComparisonExpression(greaterThan);
    }

    @Override
    public Type visit(LessThanEqual lessThanEqual) {
        return visitComparisonExpression(lessThanEqual);
    }

    @Override
    public Type visit(LessThan lessThan) {
        return visitComparisonExpression(lessThan);
    }

    //TODO: can accept both numeric and boolean
    @Override
    public Type visit(NotEqual notEqual) {
        return visitComparisonExpression(notEqual);
    }

    private Type visitComparisonExpression(BinaryOperation operation) {
        Type returnedType = checkTypeCompatibility(operation);
        //If issue logged further down the tree, don't log new error
        if (returnedType.isOfType("error")) {
            return returnedType;
        } else if (returnedType.isNumeric() || returnedType.isOfType("boolean")){
            return new BooleanType(operation.getSourceLocation());
        } else {
            issueTracker.addError(operation, String.format("Type mismatch. Comparison expression containing operands of type %s", returnedType));
            return new ErrorType(operation.getSourceLocation());
        }
    }

    @Override
    public Type visit(Multiplication multiplication) {
        return verifyType(checkTypeCompatibility(multiplication), "numeric");
    }

    @Override
    public Type visit(Or or) {
        return verifyType(checkTypeCompatibility(or), "boolean");
    }

    @Override
    public Type visit(And and) {
        return verifyType(checkTypeCompatibility(and), "boolean");
    }

    @Override
    public Type visit(Subtraction subtraction) {
        return verifyType(checkTypeCompatibility(subtraction), "numeric");
    }

    @Override
    public Type visit(Negation negation) {
        Type actualType = negation.getExpression().accept(this);
        return verifyType(actualType, "boolean");
    }

    @Override
    public Type visit(Negative negative) {
        Type actualType = negative.getExpression().accept(this);
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
        if (!symbolTable.isDeclared(variable.getName())) {
            issueTracker.addError(variable, "Reference to undefined question");
            return new ErrorType(variable.getSourceLocation());
        }
        return symbolTable.lookup(variable.getName());
    }

}