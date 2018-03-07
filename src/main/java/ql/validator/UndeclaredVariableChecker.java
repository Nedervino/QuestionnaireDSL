package ql.validator;

import ql.ast.Form;
import ql.ast.expressions.Variable;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.*;
import ql.ast.expressions.unary.ArithmeticNegation;
import ql.ast.expressions.unary.LogicalNegation;
import ql.ast.statements.*;
import ql.ast.visitors.ExpressionVisitor;
import ql.ast.visitors.FormVisitor;
import ql.ast.visitors.StatementVisitor;


public class UndeclaredVariableChecker implements FormVisitor<Void>, StatementVisitor<Void>, ExpressionVisitor<Void> {

    SymbolTable symbolTable;

    public boolean passesTests(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        visit(form);

        return true;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for (Statement statement : ifStatement.getIfStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for (Statement statement : ifElseStatement.getIfStatements()) {
            statement.accept(this);
        }
        for (Statement statement : ifElseStatement.getElseStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Question question) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        return null;
    }

    @Override
    public Void visit(Addition addition) {
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public Void visit(Division division) {
        return null;
    }

    @Override
    public Void visit(Equal equal) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqual greaterThanEqual) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public Void visit(LessThanEqual lessThanEqual) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public Void visit(LogicalNegation logicalNegation) {
        return null;
    }

    @Override
    public Void visit(ArithmeticNegation arithmeticNegation) {
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Void visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public Void visit(DecimalLiteral decimalLiteral) {
        return null;
    }

    @Override
    public Void visit(MoneyLiteral moneyLiteral) {
        return null;
    }

    @Override
    public Void visit(Variable variable){
        String varName = variable.toString();
        if(!symbolTable.isDeclared(varName)){
            try {
                throw new Exception("Variable was referenced but never declared: " + varName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
