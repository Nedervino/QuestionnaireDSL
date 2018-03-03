package ql.ast.visitors;

import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.BooleanLiteral;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.expressions.unary.MinusNode;
import ql.ast.expressions.unary.NegNode;
import ql.ast.expressions.values.IDNode;

public interface ExpressionVisitor <T> {

    // Binary expressions
    T visit(AddNode addNode);

    T visit(AndNode andNode);

    T visit(DivNode divNode);

    T visit(EqNode eqNode);

    T visit(GteNode gteNode);

    T visit(GtNode gtNode);

    T visit(LteNode lteNode);

    T visit(LtNode ltNode);

    T visit(MulNode mulNode);

    T visit(NeqNode neqNode);

    T visit(OrNode orNode);

    T visit(SubNode subNode);

    // Unary expressions
    T visit(NegNode negNode);

    T visit(MinusNode minusNode);

    // Values
    T visit(IDNode idNode);

    //Literals
    T visit(StringLiteral stringLiteral);

    T visit(IntegerLiteral integerLiteral);

    T visit(BooleanLiteral booleanLiteral);

}
