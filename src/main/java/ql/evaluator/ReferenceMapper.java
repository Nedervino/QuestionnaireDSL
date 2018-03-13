package ql.evaluator;

import ql.ast.Form;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ReferenceMapper implements FormVisitor<Void>, ExpressionVisitor<Void>, StatementVisitor<Void> {

    HashMap<String, List<Variable>> referenceMap;

    public ReferenceMapper() {
        referenceMap = new HashMap<>();
    }

    public HashMap<String, List<Variable>> getMap(Form form) {
        visit(form);

        return referenceMap;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    public void visitBinaryOperation(BinaryOperation binaryOperation) {
        binaryOperation.getLeft().accept(this);
        binaryOperation.getRight().accept(this);
    }

    public void visitUnaryOperation(UnaryOperation unaryOperation) {
        unaryOperation.getExpression().accept(this);
    }

    @Override
    public Void visit(Addition addition) {
        visitBinaryOperation(addition);
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd) {
        visitBinaryOperation(logicalAnd);
        return null;
    }

    @Override
    public Void visit(Division division) {
        visitBinaryOperation(division);
        return null;
    }

    @Override
    public Void visit(Equal equal) {
        visitBinaryOperation(equal);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqual greaterThanEqual) {
        visitBinaryOperation(greaterThanEqual);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        visitBinaryOperation(greaterThan);
        return null;
    }

    @Override
    public Void visit(LessThanEqual lessThanEqual) {
        visitBinaryOperation(lessThanEqual);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        visitBinaryOperation(lessThan);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        visitBinaryOperation(multiplication);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        visitBinaryOperation(notEqual);
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr) {
        visitBinaryOperation(logicalOr);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        visitBinaryOperation(subtraction);
        return null;
    }

    @Override
    public Void visit(LogicalNegation logicalNegation) {
        visitUnaryOperation(logicalNegation);
        return null;
    }

    @Override
    public Void visit(ArithmeticNegation arithmeticNegation) {
        visitUnaryOperation(arithmeticNegation);
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
    public Void visit(Variable node) {
        String varName = node.toString();

        List<Variable> referenceList;
        if (!referenceMap.containsKey(varName)) {
            referenceList = new LinkedList();
            referenceMap.put(varName, referenceList);
        } else {
            referenceList = referenceMap.get(varName);
        }
        referenceList.add(node);

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
        return null;
    }

    @Override
    public Void visit(Question question) {
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        computedQuestion.getExpression().accept(this);
        return null;
    }
}
