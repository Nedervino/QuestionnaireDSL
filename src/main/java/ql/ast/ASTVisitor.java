package ql.ast;


import ql.ast.expressions.IDNode;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.MinusNode;
import ql.ast.expressions.unary.NegNode;
import ql.ast.statements.ComputedQuestionNode;
import ql.ast.statements.IfStatementNode;
import ql.ast.statements.QuestionNode;
import ql.ast.types.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.StatementVisitor;
import ql.ast.visitors.TypeVisitor;

public class ASTVisitor<T> implements ExpressionVisitor<T>, StatementVisitor<T>, TypeVisitor<T> {


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

    @Override
    public T visit(MinusNode minusNode) {
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
    *    Values
    * */

    @Override
    public T visit(IDNode idNode) {
        return null;
    }


}
