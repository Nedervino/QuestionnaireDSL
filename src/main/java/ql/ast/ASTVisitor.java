package ql.ast;


import ql.ast.expressions.*;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.unary.NegNode;
import ql.ast.expressions.unary.ParNode;
import ql.ast.expressions.unary.UnOpNode;
import ql.ast.expressions.values.IDNode;
import ql.ast.expressions.values.ValNode;
import ql.ast.statements.*;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.ast.visitors.TypeVisitor;

public class ASTVisitor<T> implements ExpressionVisitor<T>, StatementVisitor<T>, TypeVisitor<T>{


    public T visit(FormNode formNode) {
        return null;
    }

    /*
    *    Statements
    **/

    @Override
    public T visit(IfStatementNode ifStatement) {
        return null;
    }

    @Override
    public T visit(QuestionNode question) {
        return null;
    }

    @Override
    public T visit(ComputedQuestionNode computedQuestion) {
        return null;
    }

    /*
    *    Binary expressions
    **/

    @Override
    public T visit(AddNode addNode) {
        return null;
    }

    @Override
    public T visit(AndNode andNode) {
        return null;
    }

    @Override
    public T visit(DivNode divNode) {
        return null;
    }

    @Override
    public T visit(EqNode eqNode) {
        return null;
    }

    @Override
    public T visit(GteNode gteNode) {
        return null;
    }

    @Override
    public T visit(GtNode gtNode) {
        return null;
    }

    @Override
    public T visit(LteNode lteNode) {
        return null;
    }

    @Override
    public T visit(LtNode ltNode) {
        return null;
    }

    @Override
    public T visit(MulNode mulNode) {
        return null;
    }

    @Override
    public T visit(NeqNode neqNode) {
        return null;
    }

    @Override
    public T visit(OpSymHelperNode opSymHelperNode) {
        return null;
    }

    @Override
    public T visit(OrNode orNode) {
        return null;
    }

    @Override
    public T visit(SubNode subNode) {
        return null;
    }

    /*
    *    Unary expressions
    * */

    @Override
    public T visit(NegNode negNode) {
        return null;
    }

    /*
    *    Values
    * */

    @Override
    public T visit(IDNode idNode) {
        return null;
    }

    @Override
    public T visit(ValNode valNode) {
        return null;
    }

    @Override
    public T visit(ParNode parNode) {
        return null;
    }

    /*
    *    Type
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

}
