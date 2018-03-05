package ql.ast;


import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.ComputedQuestion;
import ql.ast.statements.IfStatement;
import ql.ast.statements.Question;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.ast.visitors.TypeVisitor;

public class BaseASTVisitor<T> implements ExpressionVisitor<T>, StatementVisitor<T>, TypeVisitor<T> {


    public T visit(Form form) {
        return null;
    }

    /*
    *    Statements
    **/

    @Override
    public T visit(IfStatement ifStatement) {
        return null;
    }

    @Override
    public T visit(Question question) {
        return null;
    }

    @Override
    public T visit(ComputedQuestion computedQuestion) {
        return null;
    }

    /*
    *    Binary expressions
    **/

    @Override
    public T visit(Addition addition) {
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public T visit(Division division) {
        return null;
    }

    @Override
    public T visit(Equal equal) {
        return null;
    }

    @Override
    public T visit(GreaterThanEqual greaterThanEqual) {
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public T visit(LessThanEqual lessThanEqual) {
        return null;
    }

    @Override
    public T visit(LessThan lessThan) {
        return null;
    }

    @Override
    public T visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public T visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public T visit(Subtraction subtraction) {
        return null;
    }

    /*
    *    Unary expressions
    * */

    @Override
    public T visit(LogicalNegation logicalNegation) {
        return null;
    }

    @Override
    public T visit(ArithmeticNegation arithmeticNegation) {
        return null;
    }

    /*
    *    Types
    **/

    @Override
    public T visit(BooleanType booleanType) {
        return null;
    }

    @Override
    public T visit(DecimalType decimalType) {
        return null;
    }

    @Override
    public T visit(IntegerType integerType) {
        return null;
    }

    @Override
    public T visit(MoneyType moneyType) {
        return null;
    }

    @Override
    public T visit(StringType stringType) {
        return null;
    }

    @Override
    public T visit(DateType dateType) {
        return null;
    }

    /*
    *    Literals
    **/

    @Override
    public T visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public T visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public T visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public T visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public T visit(DecimalLiteral decimalLiteral) {
        return null;
    }

    @Override
    public T visit(MoneyLiteral moneyLiteral) {
        return null;
    }

    /*
    *    Identifiers
    * */

    @Override
    public T visit(Variable variable) {
        return null;
    }


}
