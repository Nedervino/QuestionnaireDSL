package ql.ast.visitors;

import ql.ast.expressions.binary.*;
import ql.ast.expressions.unary.NegNode;
import ql.ast.expressions.unary.ParNode;
import ql.ast.expressions.values.IDNode;
import ql.ast.expressions.values.ValNode;

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

    T visit(OpSymHelperNode opSymHelperNode);

    T visit(OrNode orNode);

    T visit(SubNode subNode);

    // Unary expressions
    T visit(NegNode negNode);

    // ValuesValues
    T visit(IDNode idNode);

    T visit(ValNode valNode);

    T visit(ParNode parNode);

}
