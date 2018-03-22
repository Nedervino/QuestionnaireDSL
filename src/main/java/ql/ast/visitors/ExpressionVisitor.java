package ql.ast.visitors;

import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.Negative;
import ql.ast.expressions.unary.Negation;

public interface ExpressionVisitor<T> {

    // Binary expressions
    T visit(Addition addition);

    T visit(And and);

    T visit(Division division);

    T visit(Equal equal);

    T visit(GreaterThanEqual greaterThanEqual);

    T visit(GreaterThan greaterThan);

    T visit(LessThanEqual lessThanEqual);

    T visit(LessThan lessThan);

    T visit(Multiplication multiplication);

    T visit(NotEqual notEqual);

    T visit(Or or);

    T visit(Subtraction subtraction);

    // Unary expressions
    T visit(Negation negation);

    T visit(Negative negative);

    //Literals
    T visit(StringLiteral stringLiteral);

    T visit(IntegerLiteral integerLiteral);

    T visit(BooleanLiteral booleanLiteral);

    T visit(DateLiteral dateLiteral);

    T visit(DecimalLiteral decimalLiteral);

    T visit(MoneyLiteral moneyLiteral);

    // Variable
    T visit(Variable variable);

}
